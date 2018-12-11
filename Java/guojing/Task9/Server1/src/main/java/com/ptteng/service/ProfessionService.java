package com.ptteng.service;

import com.ptteng.entity.Profession;
import org.oasisopen.sca.annotation.Remotable;

import java.util.List;

@Remotable
public interface ProfessionService {

    List<Profession> findProfession();
}
