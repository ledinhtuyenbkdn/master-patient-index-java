package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BusinessException;
import com.ledinhtuyenbkdn.masterpersonindex.model.MasterPerson;
import com.ledinhtuyenbkdn.masterpersonindex.model.Person;
import com.ledinhtuyenbkdn.masterpersonindex.model.ReviewLink;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.PersonStatus;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MasterPersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.PersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.ReviewLinkRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.ReviewLinkService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewLinkServiceImpl implements ReviewLinkService {

    private ReviewLinkRepository reviewLinkRepository;

    private PersonRepository personRepository;

    private MasterPersonRepository masterPersonRepository;

    public ReviewLinkServiceImpl(ReviewLinkRepository reviewLinkRepository, PersonRepository personRepository, MasterPersonRepository masterPersonRepository) {
        this.reviewLinkRepository = reviewLinkRepository;
        this.personRepository = personRepository;
        this.masterPersonRepository = masterPersonRepository;
    }

    @Override
    public Optional<ReviewLink> findOne(Long id) {
        return reviewLinkRepository.findById(id);
    }

    @Override
    public List<ReviewLink> findAll() {
        return reviewLinkRepository.findAll();
    }

    @Override
    public void matchPersonToMasterPerson(Long reviewLinkId) {
        Optional<ReviewLink> reviewLinkOptional = reviewLinkRepository.findById(reviewLinkId);

        if (!reviewLinkOptional.isPresent()) {
            throw new BusinessException("Not found review link");
        }

        ReviewLink reviewLink = reviewLinkOptional.get();
        Person person = reviewLink.getPerson();
        MasterPerson masterPerson = reviewLink.getMasterPerson();

        person.setMasterPerson(masterPerson);
        person.setPersonStatus(PersonStatus.MANUAL_MATCH);
        person.setScore(reviewLink.getScore());
        personRepository.save(person);

        List<ReviewLink> reviewLinks = reviewLinkRepository.findAllByPersonId(person.getId());
        reviewLinkRepository.deleteAll(reviewLinks);
    }

    @Override
    public void rejectLinkPersonToMasterPerson(Long reviewLinkId) {
        Optional<ReviewLink> reviewLinkOptional = reviewLinkRepository.findById(reviewLinkId);

        if (!reviewLinkOptional.isPresent()) {
            throw new BusinessException("Not found review link");
        }

        ReviewLink reviewLink = reviewLinkOptional.get();
        Person person = reviewLink.getPerson();
        List<ReviewLink> reviewLinks = reviewLinkRepository.findAllByPersonId(person.getId());

        if (reviewLinks.size() == 1) {
            person.setPersonStatus(PersonStatus.NEW_MASTER_PERSON);
            person.setScore(100.0);
            personRepository.save(person);
        }

        reviewLinkRepository.deleteAll(reviewLinks);
    }
}
