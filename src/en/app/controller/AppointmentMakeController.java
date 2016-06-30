package en.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import en.app.dto.AppointmentDTO;
import en.app.dto.CashDTO;
import en.app.service.IAppointmentService;
import en.app.service.ICashBackService;
import en.app.service.ICashService;
import en.basis.service.ICompanyService;
import en.common.controller.GridController;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.ResultEntity;
import en.common.util.helper.StringUtil;
import en.dm.entity.Company;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by syb on 2016/1/21.
 */

@RestController
@RequestMapping("app")
public class AppointmentMakeController extends GridController{

    @Resource
    private IAppointmentService appointmentService;
    @Resource
    private ICashService cashService;
    @Resource
    private ICashBackService cashBackService;
    @Resource
    private ICompanyService companyService;


    /**
     * 大额预约管理
     *
     * @param model
     */
    @RequestMapping("/make")
    public ModelAndView getAppointmentJsp(Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        super.setPageCss(currentUser, model);
        return new ModelAndView("pro/jsp/app/make");
    }

    @RequestMapping("/appointment")
    public void updateAppointment(String name,String account,String number,String phone, String date, String way,String remark,String nodeNumber,String staff) {
        //System.out.println("name: "+name+"   account: "+account);

        AppointmentDTO adto = new AppointmentDTO();
        adto.setName(name);
        adto.setAccount(account);
        adto.setNumber(Double.parseDouble(number));
        adto.setPhone(phone);
        adto.setRemark(remark);
        adto.setWay(way);
        adto.setNodeNumber(nodeNumber);
        adto.setReserve(staff);//将保留字段用作预约人的字段

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date dateDate = null;
        try{
            dateDate = sdf.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }

        //限制在每天17:30之前做预约，且当天只能做第二天及以后时间的预约
        Date currrentDate = new Date();
        Date limitDate = new Date(currrentDate.getYear(), currrentDate.getMonth(), currrentDate.getDate(), 17, 30);
        if (currrentDate.after(limitDate)) {
            this.setResultValue("{failure:true,Msg:'请在每天17:30之前预约'}");
        } else if (currrentDate.after(dateDate)) {
            this.setResultValue("{failure:true,Msg:'支取日期应大于今天'}");
        } else {
            adto.setDate(dateDate);

            this.gridSerivce = appointmentService;
            super.updateBillByDTO(adto);
        }

    }

    @RequestMapping("summary")
    public ModelAndView getSummaryJsp(Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        super.setPageCss(currentUser, model);

        //String str = "{ isRole : 'Y' ,tn_isToolbarAdd : false ,tn_isToolbarDel : true ,tn_isToolbarSave : true ,tn_isToolbarPrint : false ,tn_isToolbarSearch : true }";
        String str = getComRole(currentUser,"APPSUMMARY");
        model.addAttribute("detailRole", str);
        return new ModelAndView("pro/jsp/app/summary");
    }

    @RequestMapping("summary/loaddata")
    public void loadAppointmentSummaryData(String gsdm,String date,int start,int limit,Model model) {

        //System.out.println("date: "+date);
        try{
            String cond = "";
            if(!StringUtil.isEmpty(gsdm)){
                cond = cond+" and gsdm = '"+gsdm+"'";
            }

            if(!StringUtil.isEmpty(date)){
//                cond = cond+" and data_type like '%"+ ChineseUtil.getString(anyField)+"%'";
//                date = date.replace("年","-");
//                date = date.replace("月","-");
//                date = date.replace("日","");
                date = date.substring(0,10);
                cond = cond+" and date = '"+date+"'" +"and status=true";
            }

            ResultEntity re =  appointmentService.findPageResult(getSessionUser(),cond, start, limit);
            if(re.getResultType() == 0)
                this.setResultValue(re.getResultDesc().toString());
            else
                this.setStoreErrorResult(re.getResultDesc().toString());


        }catch (Exception e) {
            e.printStackTrace();
            this.setStoreErrorResult(getErrMsg(e));
        }

    }

    @RequestMapping("summary/remove")
    public void removeAppointmentSummaryData(String selectedId){
        if(StringUtil.isEmpty(selectedId)){
            this.setErrorResultValue("必需选择要删除的记录");
        }
        try{
            SessionUser sessionuser = getSessionUser();
            appointmentService.remove(sessionuser,selectedId);
            this.setResultValue("OK");
        }catch (Exception e) {
            e.printStackTrace();
            this.setErrorResultValue(getErrMsg(e));
        }
    }

    @RequestMapping("/summary/update")
    public void updateAppointmentSummary(String jsonData,Model model) {
        if(StringUtil.isEmpty(jsonData)){
            this.setErrorResultValue("无效的数据");
            return;
        }
        try{
            appointmentService.updateBill(getSessionUser(), jsonData);
            this.setResultValue("OK");
        }catch (Exception e) {
            e.printStackTrace();
            this.setErrorResultValue(getErrMsg(e));
        }
    }

    @RequestMapping("/cash")
    public ModelAndView getCashJsp(Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        super.setPageCss(currentUser, model);
        return new ModelAndView("pro/jsp/app/cash");
    }

    @RequestMapping("/cash/make")
    public void updateCash(String number, String date,String remark,String nodeNumber,String staff) {

        System.out.println("金额:"+number+" 日期:"+date+" 备注:"+remark+ " 网点号:"+nodeNumber+ " 员工:"+staff);
        CashDTO cdto = new CashDTO();
        cdto.setNumber(Double.parseDouble(number));
        cdto.setRemark(remark);
        cdto.setNodeNumber(nodeNumber);
        cdto.setStaff(staff);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date dateDate = null;
        try{
            dateDate = sdf.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        cdto.setDate(dateDate);

        //限制在每天17:30之前做预约，且当天只能做第二天及以后时间的预约
        Date currrentDate = new Date();
        Date limitDate = new Date(currrentDate.getYear(), currrentDate.getMonth(), currrentDate.getDate(), 17, 30);
        if (currrentDate.after(limitDate)) {
            this.setResultValue("{failure:true,Msg:'请在每天17:30之前预约'}");
        } else if (currrentDate.after(dateDate)) {
            this.setResultValue("{failure:true,Msg:'支取日期应大于今天'}");
        } else {
            this.gridSerivce = cashService;
            super.updateBillByDTO(cdto);
        }

    }

    @RequestMapping("cash/summary")
    public ModelAndView getCashSummaryJsp(Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        super.setPageCss(currentUser, model);

        //String str = "{ isRole : 'Y' ,tn_isToolbarAdd : false ,tn_isToolbarDel : true ,tn_isToolbarSave : true ,tn_isToolbarPrint : false ,tn_isToolbarSearch : true }";
        String str = getComRole(currentUser,"APPCASHSUMMARY");
        System.out.println("role: "+str);
        model.addAttribute("detailRole", str);
        return new ModelAndView("pro/jsp/app/cashsummary");
    }

    @RequestMapping("cash/summary/loaddata")
    public void loadCashSummaryData(String gsdm,String date,int start,int limit,Model model) {

        try{
            String cond = "";
            if(!StringUtil.isEmpty(gsdm)){
                cond = cond+" and gsdm = '"+gsdm+"'";
            }

            if(!StringUtil.isEmpty(date)){
                date = date.substring(0,10);
                cond = cond+" and date = '"+date+"'" +"and status=true";
            }

            ResultEntity re =  cashService.findPageResult(getSessionUser(),cond, start, limit);
            if(re.getResultType() == 0)
                this.setResultValue(re.getResultDesc().toString());
            else
                this.setStoreErrorResult(re.getResultDesc().toString());


        }catch (Exception e) {
            e.printStackTrace();
            this.setStoreErrorResult(getErrMsg(e));
        }

    }

    @RequestMapping("/cash/summary/remove")
    public void removeCashSummaryData(String selectedId){
        if(StringUtil.isEmpty(selectedId)){
            this.setErrorResultValue("必需选择要删除的记录");
        }
        try{
            SessionUser sessionuser = getSessionUser();
            cashService.remove(sessionuser,selectedId);
            this.setResultValue("OK");
        }catch (Exception e) {
            e.printStackTrace();
            this.setErrorResultValue(getErrMsg(e));
        }
    }

    @RequestMapping("/cash/summary/update")
    public void updateCashSummary(String jsonData,Model model) {
        if(StringUtil.isEmpty(jsonData)){
            this.setErrorResultValue("无效的数据");
            return;
        }
        try{
            cashService.updateBill(getSessionUser(), jsonData);
            this.setResultValue("OK");
        }catch (Exception e) {
            e.printStackTrace();
            this.setErrorResultValue(getErrMsg(e));
        }
    }

    @RequestMapping("/cashback")
    public ModelAndView getCashBackJsp(Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        super.setPageCss(currentUser, model);

        //String str = "{ isRole : 'Y' ,tn_isToolbarAdd : false ,tn_isToolbarDel : true ,tn_isToolbarSave : true ,tn_isToolbarPrint : false ,tn_isToolbarSearch : true }";
        String str = getComRole(currentUser,"APPCASHBACK");
        System.out.println("role: "+str);
        model.addAttribute("detailRole", str);
        return new ModelAndView("pro/jsp/app/cashback");
    }

    @RequestMapping("/cashback/loaddata")
    public void loadCashBackData(String gsdm,String date,int start,int limit,Model model) {

        try{
            String cond = "";
            if(!StringUtil.isEmpty(gsdm)){
                cond = cond+" and gsdm = '"+gsdm+"'";
            }

            if(!StringUtil.isEmpty(date)){
                date = date.substring(0,10);
                cond = cond+" and date = '"+date+"'" +"and status=true";
            }

            ResultEntity re =  cashBackService.findPageResult(getSessionUser(),cond, start, limit);
            if(re.getResultType() == 0)
                this.setResultValue(re.getResultDesc().toString());
            else
                this.setStoreErrorResult(re.getResultDesc().toString());


        }catch (Exception e) {
            e.printStackTrace();
            this.setStoreErrorResult(getErrMsg(e));
        }

    }

    @RequestMapping("/cashback/remove")
    public void removeCashBackData(String selectedId){
        if(StringUtil.isEmpty(selectedId)){
            this.setErrorResultValue("必需选择要删除的记录");
        }
        try{
            SessionUser sessionuser = getSessionUser();
            cashBackService.remove(sessionuser,selectedId);
            this.setResultValue("OK");
        }catch (Exception e) {
            e.printStackTrace();
            this.setErrorResultValue(getErrMsg(e));
        }
    }

    @RequestMapping("/cashback/update")
    public void updateCashBack(String jsonData,Model model) {
        if(StringUtil.isEmpty(jsonData)){
            this.setErrorResultValue("无效的数据");
            return;
        }
        try{
            cashBackService.updateBill(getSessionUser(), jsonData);
            this.setResultValue("OK");
        }catch (Exception e) {
            e.printStackTrace();
            this.setErrorResultValue(getErrMsg(e));
        }
    }

    @RequestMapping("/cashback/make")
    public void updateCashBack(String number, String date,String remark,String nodeNumber,String staff) {

        System.out.println("金额:"+number+" 日期:"+date+" 备注:"+remark+ " 网点号:"+nodeNumber+ " 员工:"+staff);
        CashDTO cdto = new CashDTO();
        cdto.setNumber(Double.parseDouble(number));
        cdto.setRemark(remark);
        cdto.setNodeNumber(nodeNumber);
        cdto.setStaff(staff);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date dateDate = null;
        try{
            dateDate = sdf.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        cdto.setDate(dateDate);

        this.gridSerivce = cashBackService;
        super.updateBillByDTO(cdto);

    }

    @RequestMapping("/getNodes")
    public void getAllNodesOrCompanies(){
        try{
            List<Company> companyList = companyService.getCompaniesList();
            List<en.dm.entity.Node> nodeList = new ArrayList<>();
            for (int i=0;i<companyList.size();i++){
                Company company = companyList.get(i);
                en.dm.entity.Node node = new en.dm.entity.Node();
                node.setName(company.getName());
                node.setNodeNumber(company.getNodeNumber());
                nodeList.add(node);
            }
            Map<String,Object> map = new HashMap<>();
            map.put("success",true);
            map.put("data",nodeList);

            ObjectMapper mapper = new ObjectMapper();
            String jsonResult = mapper.writeValueAsString(map);
            this.setResultValue(jsonResult);
            System.out.println("找到的所有公司是：");
            System.out.println(jsonResult);
        }catch (Exception e){
            e.printStackTrace();
            this.setErrorResultValue(getErrMsg(e));
        }
    }

}
