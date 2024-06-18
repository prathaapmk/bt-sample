package com.bt.sample.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

import com.bt.sample.core.services.GetPathService;

@Component(service = GetPathService.class,immediate = true)
@ServiceRanking(value=999)
public class GetAssetPathServiceImpl1 implements GetPathService{

	@Override
	public String getPathOfTheAsset() {

		return "/content/myAsset/Path1";
	}

}
