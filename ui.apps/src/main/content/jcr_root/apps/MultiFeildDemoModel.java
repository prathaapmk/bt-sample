package apps;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiFeildDemoModel {

	
	@ValueMapValue
	private String[]  states;
	
	@ChildResource
	List<CountrywithCurrencies> multicurandcountry;
	

	public String[] getStates() {
		return states;
	}

	public List<CountrywithCurrencies> getMulticurandcountry() {
		return multicurandcountry;
	}
	
	
	
}