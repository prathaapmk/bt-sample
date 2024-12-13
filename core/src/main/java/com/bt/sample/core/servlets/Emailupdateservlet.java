package com.bt.sample.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bt.sample.core.utils.CommonUtils;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/updateemail")
public class Emailupdateservlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(Emailupdateservlet.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {
        updateEmail(request, response);
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        updateEmail(request, response);
    }

    private void updateEmail(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws IOException {
        response.setContentType("text/plain");

        try (ResourceResolver resolver = CommonUtils.getupdateResolver(resolverFactory)) {
            String newEmail = request.getParameter("newEmail");

            if (newEmail == null || newEmail.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("New email parameter is missing or empty.");
                return;
            }

            String parentPath = "/content/bt-sample-project/us/en";

            Resource parentResource = resolver.getResource(parentPath);
            LOG.info(resolver.getUserID());

            if (parentResource != null) {
                Iterable<Resource> childResources = parentResource.getChildren();

                for (Resource child : childResources) {
                    updateEmailInResource(child, newEmail, resolver);
                }

                resolver.commit();
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Email updated successfully in all child pages.");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Parent resource not found.");
            }
        } catch (Exception e) {
            LOG.error("Error updating email in child pages: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating email in child pages: " + e.getMessage());
        }
    }

    private void updateEmailInResource(Resource childPage, String newEmail, ResourceResolver resolver) {
        try {
            // Navigate to the jcr:content node under the child page node
            Resource jcrContent = childPage.getChild("jcr:content");

            if (jcrContent != null) {
                ModifiableValueMap properties = jcrContent.adaptTo(ModifiableValueMap.class);
                if (properties != null) {
                    properties.put("email", newEmail);
                    resolver.commit();
                    LOG.info("Updated email '{}' for resource: {}", newEmail, jcrContent.getPath());
                } else {
                    LOG.warn("Properties could not be adapted for resource: {}", jcrContent.getPath());
                }
            } else {
                LOG.warn("jcr:content node not found under resource: {}", childPage.getPath());
            }
        } catch (PersistenceException e) {
            LOG.error("PersistenceException while updating email: {}", e.getMessage(), e);
        }
    }
}
