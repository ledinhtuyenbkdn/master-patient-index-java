package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.Setting;
import com.ledinhtuyenbkdn.masterpersonindex.repository.SettingRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.SettingService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SettingServiceImpl implements SettingService {

    private SettingRepository settingRepository;

    public SettingServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public List<Setting> update(List<Setting> settings) {
        return settingRepository.saveAll(settings);
    }

    @Override
    public List<Setting> findAll() {
        return settingRepository.findAll();
    }

    @Override
    public <T> T getSettingValue(String key, Class<T> type) {
        Optional<Setting> settingOptional = settingRepository.findByMpiKey(key);

        if (!settingOptional.isPresent()) {
            throw new RuntimeException("Setting key is illegal");
        }

        Setting setting = settingOptional.get();
        String settingValue = setting.getMpiValue();

        if (type == String.class) {
            return type.cast(settingValue);
        }

        if (type == Long.class) {
            return type.cast(Long.valueOf(settingValue));
        }

        return null;
    }
}
