package com.ledinhtuyenbkdn.masterpersonindex.service.algorithm;

import com.ledinhtuyenbkdn.masterpersonindex.model.Person;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Gender;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MasterPersonRepository;
import org.springframework.stereotype.Service;

@Service
public class GenerateGlobalIdServiceImpl implements GenerateGlobalIdService {

    private MasterPersonRepository masterPersonRepository;

    public GenerateGlobalIdServiceImpl(MasterPersonRepository masterPersonRepository) {
        this.masterPersonRepository = masterPersonRepository;
    }

    @Override
    public String generate(Person person) {
        boolean lackOfInfo = person.getProvince() == null || person.getDateOfBirth() == null || person.getGender() == null;

        if (lackOfInfo) {
            return "";
        }

        String result = "";

        //phan 1: ma tinh, thanh pho
        result += person.getProvince().getCode();

        //phan 2: nam sinh + gioi tinh
        int yearOfBirth = person.getDateOfBirth().getYear();
        Gender gender = person.getGender();

        if (yearOfBirth < 2000) {
            if (gender == Gender.MALE) {
                result += "0";
            } else {
                result += "1";
            }
        } else if (yearOfBirth < 2100) {
            if (gender == Gender.MALE) {
                result += "2";
            } else {
                result += "3";
            }
        } else if (yearOfBirth < 2200) {
            if (gender == Gender.MALE) {
                result += "4";
            } else {
                result += "5";
            }
        } else {
            if (gender == Gender.MALE) {
                result += "8";
            } else {
                result += "7";
            }
        }

        if (yearOfBirth % 100 == 0) {
            result += "00";
        } else {
            result += String.valueOf(yearOfBirth % 100);
        }

        //phan 3: day so tang tu dong
        Long nextMasterPersonId = getMaxMasterPersonId() + 1;
        for (int i = String.valueOf(nextMasterPersonId).length(); i < 6; i++) {
            result += "0";
        }
        result += String.valueOf(nextMasterPersonId);

        //phan 4: 1 ki tu cuoi
        result += generateCheckDigit(result);

        return result;
    }

    public Long getMaxMasterPersonId() {
        Long result = 0L;
        if (masterPersonRepository.count() > 0) {
            result = masterPersonRepository.getMaxMasterPersonId();
        }
        return result;
    }

    public static char generateCheckDigit(String numberStr) {
        int weight = 2;
        long sum = 0;
        long number = Long.parseLong(numberStr);
        while (number > 0) {
            sum += (number % 10) * weight;
            number /= 10;
            weight++;
        }

        long remainder = sum % 11;
        if (remainder == 0) {
            return '0';
        } else if (remainder == 1) {
            return 'X';
        } else {
            return (char) (11 - remainder + '0');
        }
    }

    public static boolean checkNumber(String numberStr) {
        int weight = 1;
        int sum = 0;
        if (numberStr.charAt(numberStr.length() - 1) == 'X') {
            sum = 10;
            weight = 2;
        }
        long number = Long.parseLong(numberStr.substring(0, numberStr.length() - 1));
        while (number > 0) {
            sum += (number % 10) * weight;
            number /= 10;
            weight++;
        }
        return sum % 11 == 0;
    }
}
