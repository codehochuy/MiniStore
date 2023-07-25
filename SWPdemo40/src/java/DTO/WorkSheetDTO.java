/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Vuong
 */
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Vuong
 */
public class WorkSheetDTO {
  private int shiftid;
  private String shiftname;
//employeerole
   private int employeerole;
   //time
   private Time starttime;
     private Time endtime;
     //date
  private Date startdate;
  private int dayofweekfirst;
   private Date enddate;
   private int dayofweeksecond;
  
   //coef salary
     private float coefsalary;
     
     //id employee
   public  WorkSheetEmployeeFirstDTO  worksheetemployeefirstdto;
   public  WorkSheetEmployeeSecondDTO  worksheetemployeeseconddto;
     
 public WorkSheetDTO() {
    }

    public WorkSheetDTO(int shiftid, String shiftname, int employeerole, Time starttime, Time endtime, Date startdate, int dayofweekfirst, Date enddate, int dayofweeksecond, float coefsalary, WorkSheetEmployeeFirstDTO worksheetemployeefirstdto, WorkSheetEmployeeSecondDTO worksheetemployeeseconddto) {
        this.shiftid = shiftid;
        this.shiftname = shiftname;
        this.employeerole = employeerole;
        this.starttime = starttime;
        this.endtime = endtime;
        this.startdate = startdate;
        this.dayofweekfirst = dayofweekfirst;
        this.enddate = enddate;
        this.dayofweeksecond = dayofweeksecond;
        this.coefsalary = coefsalary;
        this.worksheetemployeefirstdto = worksheetemployeefirstdto;
        this.worksheetemployeeseconddto = worksheetemployeeseconddto;
    }

    public int getShiftid() {
        return shiftid;
    }

    public void setShiftid(int shiftid) {
        this.shiftid = shiftid;
    }

    public String getShiftname() {
        return shiftname;
    }

    public void setShiftname(String shiftname) {
        this.shiftname = shiftname;
    }

    public int getEmployeerole() {
        return employeerole;
    }

    public void setEmployeerole(int employeerole) {
        this.employeerole = employeerole;
    }

    public Time getStarttime() {
        return starttime;
    }

    public void setStarttime(Time starttime) {
        this.starttime = starttime;
    }

    public Time getEndtime() {
        return endtime;
    }

    public void setEndtime(Time endtime) {
        this.endtime = endtime;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public int getDayofweekfirst() {
        return dayofweekfirst;
    }

    public void setDayofweekfirst(int dayofweekfirst) {
        this.dayofweekfirst = dayofweekfirst;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getDayofweeksecond() {
        return dayofweeksecond;
    }

    public void setDayofweeksecond(int dayofweeksecond) {
        this.dayofweeksecond = dayofweeksecond;
    }

    public float getCoefsalary() {
        return coefsalary;
    }

    public void setCoefsalary(float coefsalary) {
        this.coefsalary = coefsalary;
    }

    public WorkSheetEmployeeFirstDTO getWorksheetemployeefirstdto() {
        return worksheetemployeefirstdto;
    }

    public void setWorksheetemployeefirstdto(WorkSheetEmployeeFirstDTO worksheetemployeefirstdto) {
        this.worksheetemployeefirstdto = worksheetemployeefirstdto;
    }

    public WorkSheetEmployeeSecondDTO getWorksheetemployeeseconddto() {
        return worksheetemployeeseconddto;
    }

    public void setWorksheetemployeeseconddto(WorkSheetEmployeeSecondDTO worksheetemployeeseconddto) {
        this.worksheetemployeeseconddto = worksheetemployeeseconddto;
    }

    @Override
    public String toString() {
        return "WorkSheetDTO{" + "shiftid=" + shiftid + ", shiftname=" + shiftname + ", employeerole=" + employeerole + ", starttime=" + starttime + ", endtime=" + endtime + ", startdate=" + startdate + ", dayofweekfirst=" + dayofweekfirst + ", enddate=" + enddate + ", dayofweeksecond=" + dayofweeksecond + ", coefsalary=" + coefsalary + ", worksheetemployeefirstdto=" + worksheetemployeefirstdto + ", worksheetemployeeseconddto=" + worksheetemployeeseconddto + '}';
    }

}