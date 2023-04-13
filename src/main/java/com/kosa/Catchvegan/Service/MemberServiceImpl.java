package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.Mapper.ManagerMapper;
import com.kosa.Catchvegan.Mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BCryptPasswordEncoder pe;


    //Member
    @Override
    public List<MemberDTO> findAllMembers(){
        return memberMapper.findAllMembers();
    }
    @Override
    public MemberDTO createMember(MemberDTO member){
        String pw = member.getPassword();
        member.setPassword(pe.encode(pw));
        memberMapper.createMember(member);
        memberMapper.userRole(member.getMemberIdx());
        return null;
    }

    @Override
    public boolean getId(String id){
        return memberMapper.idGet(id) == null? false : true;
    }

    @Override
    public MemberDTO getUserByIdAndPassword(String id) {
        return memberMapper.getUserByIdAndPassword(id);
    }


<<<<<<< HEAD
=======
    //Manager
    @Override
    public List<ManagerDTO> findAllManagers() {
        return managerMapper.findAllManagers();
    }

    @Override
    public ManagerDTO createManager(ManagerDTO manager) {
        String pw = manager.getPassword();
        manager.setPassword(pe.encode(pw));
        managerMapper.createManager(manager);
        managerMapper.managerRole(manager.getManagerIdx());
        return null;
    }

    @Override
    public boolean managerIdGet(String id) {
        return managerMapper.managerIdGet(id) == null? false : true;
    }

    @Override
    public ManagerDTO managerGetUserByIdAndPassword(String id) {
        return managerMapper.managerGetUserByIdAndPassword(id);
    }
>>>>>>> 1abfa0db33cc6f1e4d3c431ec5e9fe1361812dea




}
