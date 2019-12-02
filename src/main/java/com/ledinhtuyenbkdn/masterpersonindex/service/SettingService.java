package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.model.Setting;

import java.util.List;

public interface SettingService {

    List<Setting> update(List<Setting> settings);

    List<Setting> findAll();

    <T> T getSettingValue(String key, Class<T> type);
}
