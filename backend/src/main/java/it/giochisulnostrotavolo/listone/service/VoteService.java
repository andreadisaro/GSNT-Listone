package it.giochisulnostrotavolo.listone.service;

import it.giochisulnostrotavolo.listone.entities.Vote;

public interface VoteService {

    public Vote save(Vote vote);

    public void delete(Vote vote);

    public Vote findById(
            Vote.VotePK votePK);

    public java.util.List<Vote> findAll();

}
