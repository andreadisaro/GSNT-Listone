package it.giochisulnostrotavolo.listone.serviceImpl;

import it.giochisulnostrotavolo.listone.service.VoteService;
import it.giochisulnostrotavolo.listone.repository.VoteRepository;
import it.giochisulnostrotavolo.listone.entities.Vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    public void delete(Vote vote) {
        voteRepository.delete(vote);
    }

    public Vote findById(
            Vote.VotePK votePK) {
        return voteRepository.findById(votePK).orElse(null);
    }

    public java.util.List<Vote> findAll() {
        return voteRepository.findAll();
    }

}
