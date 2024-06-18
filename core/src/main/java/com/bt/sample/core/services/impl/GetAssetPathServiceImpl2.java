package com.bt.sample.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

import com.bt.sample.core.services.GetPathService;

@Component(service = GetPathService.class,immediate = true)
@ServiceRanking(value=1000)
public class GetAssetPathServiceImpl2 implements GetPathService{

	@Override
	public String getPathOfTheAsset() {
		// TODO Auto-generated method stub
		return "/content/MyAsset/Path2";
	}

}
