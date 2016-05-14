package com.stx.zzq.common.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.zzq.common.dao.IconDao;
import com.stx.zzq.common.service.IconService;

@Service
public class IconServiceIml implements IconService {

	@Autowired
	private IconDao iconDao;
	
	@Override
	public boolean saveIcon(List files) {
		
		return false;
	}

}
