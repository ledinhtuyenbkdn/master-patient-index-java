package com.ledinhtuyenbkdn.masterpersonindex.service.algorithm;

import com.ledinhtuyenbkdn.masterpersonindex.model.MasterPerson;
import com.ledinhtuyenbkdn.masterpersonindex.model.Person;

import java.util.List;

public interface BlockingService {

    List<MasterPerson> getCandidates(Person person);
}
