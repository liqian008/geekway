package com.bruce.geekway.service.klh;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhSettingCriteria;

public interface IKlhSettingService extends IFoundationService<KlhSetting, Integer, KlhSettingCriteria> {

	public KlhSetting loadKlhSetting();

}