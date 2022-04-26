package com.watercooler.daos;

import com.watercooler.entities.UsernamePasswordApplicant;


public interface UsernamePasswordApplicantDAOInterface {

    UsernamePasswordApplicant createAccountApplicant(UsernamePasswordApplicant createdApplicant);

    UsernamePasswordApplicant selectApplicantById(int id);

    int verifyUsernamePasswordApplicant(UsernamePasswordApplicant UnPw);
}
