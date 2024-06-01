package com.example.aiot_home.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.aiot_home.mapper.AccountMapper;
import com.example.aiot_home.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
public class AccountsService {
    private SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
    FaceApi faceApi=new FaceApi();

    @Autowired
    AccountMapper accountMapper;

    @GetMapping("/SelectAll")
    public String SelectAll(){
        List<Account> accounts = accountMapper.selectList(null);
        String Json= JSONObject.toJSONString(accounts);
        return Json;
    }

    @PostMapping("SelectAccountLogin")
    public String SelectAccountLogin(@RequestBody Account account){
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",account.getPhone());
        queryWrapper.eq("pwd",account.getPwd());
        List<Account> accounts = accountMapper.selectList(queryWrapper);
        if(accounts.size()>0){
            if(accounts.get(0).getIdentity().equals("管理员")){
                return account.getPhone();
            }else{
                return "Permissions";
            }
        }
        return "ERRO";
    }

    @PostMapping("/SelectAccountByPhone")
    public String SelectAccountByUser(String phone){
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        List<Account> accounts = accountMapper.selectList(queryWrapper);
        Account account=accounts.get(0);
        String Json=JSONObject.toJSONString(account);
        return Json;
    }

    @PostMapping("SelectAccountByFamilyId")
    public String SelectAccountByFamilyId(String familyid){
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("familyid",familyid);
        List<Account> accounts = accountMapper.selectList(queryWrapper);
        String Json =JSONObject.toJSONString(accounts);
        return Json;
    }

    @PostMapping("/InsertAccount")
    public String InsertAccount(@RequestBody Account account){
        System.out.println(account);
        int insert = accountMapper.insert(account);
        if(insert>0){
            return "OK";
        }else{
            return "ERRO";
        }

    }

    @PostMapping("/InsertAdmin")
    public String InsertAdmin(@RequestBody Account account){
        String familyid=dateFormat.format(new Date());
        account.setFamilyid(familyid);
        faceApi.CreateFaceDatabse(familyid);
        int insert = accountMapper.insert(account);
        if(insert>0){
            return "OK";
        }
        else{
            return "ERRO";
        }
    }

    @PostMapping("/UpdateAccount")
    public String UpdateAccount(@RequestBody Account account){
        QueryWrapper<Account> accountQueryWrapper=new QueryWrapper<>();
        accountQueryWrapper.eq("id",account.getId());
        int update = accountMapper.update(account, accountQueryWrapper);
        if(update>0){
            return "OK";
        }else{
            return "ERRO";
        }
    }

    @PostMapping("/DeleteAccountRow")
    public String DeleteAccountRow(@RequestBody Account account){
        if(account.getFaceid()!=null){
            faceApi.DelectFace(String.valueOf(account.getFamilyid()),account.getFaceid());
        }
        int i = accountMapper.deleteById(account.getId());
        if(i>0){
            return "OK";
        }else{
            return "ERRO";
        }
    }

    @PostMapping("/UpdateFace")
    public String UpdateFace(HttpServletRequest req) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        String familyid=req.getParameter("familyid");
        String faceid = req.getParameter("faceid");
        System.out.println("获取到的ID为："+id);
        System.out.println("获取到的familyid为："+familyid);
        System.out.println("获取到的faceid为："+faceid);
        Part face=req.getPart("face");
        InputStream inputStream=face.getInputStream();
        byte[] bytes=inputStream.readAllBytes();
        String base = Base64.getEncoder().encodeToString(bytes);
        if(faceid==null){
            String newfaceid = faceApi.CreateFace(familyid, base);
            accountMapper.UpdateFaceId(id, newfaceid);
        }else{
            faceApi.DelectFace(familyid,faceid);
            String newfaceid = faceApi.CreateFace(familyid, base);
            accountMapper.UpdateFaceId(id, newfaceid);
        }
        return "OK";
    }
}
