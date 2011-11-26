/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;


@Entity(name = "TestCase")
public class TestCase {

    @Id    
    @GeneratedValue
    @Column(name="testcase_id")
    private Long id;   
    
    @Basic
    @NotBlank
    private String testcasename;
    
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="testplan_id",nullable = false)  
    @Transient
    private TestPlan testplan;  
    
    @Basic
    private String testplanName;
    @Basic
    private String testcasesummary;
    @Basic
    private String testcasepre;
    @Basic
    private String testcasesteps;
    @Basic
    private String testcasepass;
    @Basic
    private String testcaseOS;
    @Basic
    private boolean notrun;
    @Basic
    private boolean passed;
    @Basic
    private boolean failed;
    @Basic
    private boolean inprogress;
    @Basic
    private boolean deferred;
    @Basic
    private boolean blocked;
    @Basic
    private String user;
    
    public TestCase() {	
    }
    
    public TestCase(TestPlan testplan,String testcasename ,String testcasesummary,String testcasepre,String testcasesteps,String testcasepass,String testcaseOS) {
    	this(testplan,testcasename,testcasesummary,testcasepre,testcasesteps,testcasepass,testcaseOS,true,false,false,false,false,false);
    }

    public TestCase( TestPlan testplan,String testcasename ,String testcasesummary,String testcasepre,String testcasesteps,String testcasepass,String testcaseOS, Boolean notrun, Boolean passed, Boolean failed, Boolean inprogress, Boolean deferred, Boolean blocked) {
    	this.testplan = testplan;
    	this.testcasename = testcasename;
		this.testcasesummary = testcasesummary;
		this.testcasepre = testcasepre;
		this.testcasesteps = testcasesteps;
		this.testcasepass = testcasepass;
		this.testcaseOS = testcaseOS;
		this.notrun = notrun;
		this.passed = passed;
		this.failed = failed;
		this.inprogress = inprogress;
		this.deferred = deferred;
		this.blocked = blocked;
    }
 // Get TestPlan
    ////////////      
	public TestPlan getTestPlan() {
		return this.testplan;
	}
 
	public void setTestPlan(TestPlan testplan) {
		this.testplan = testplan;
	}
  /////////////////
    /*public TestPlan getTestPlan()
    {
    	return testplan;
    }
    public void setTestPlan(TestPlan testplan)
    {
    	this.testplan = testplan;
    }*/
 // ID 
    public Long getId() {
    	return id;
    }
 // Testplan name
    public String getTestplanName()
    {
    	return testplanName;
    }	
    public void setTestplanName(String testplanName) 
    {
    	this.testplanName = testplanName;
    }
// Testcasename
    public String getTestcasename()
    {
    	return testcasename;
    }	
    public void setTestcasename(String testcasename) 
    {
    	this.testcasename = testcasename;
    }
// testcasesummary
    public String getTestcasesummary() 
    {
    	return testcasesummary;
    }
    public void setTestcasesummary(String testcasesummary)
    {
    	this.testcasesummary = testcasesummary;
    }
 // testcasepre
    public String getTestcasepre() 
    {
    	return testcasepre;
    }
    public void setTestcasepre(String testcasepre)
    {
    	this.testcasepre = testcasepre;
    }
 // testcasesteps
    public String getTestcasesteps() 
    {
    	return testcasesteps;
    }
    public void setTestcasesteps(String testcasesteps)
    {
    	this.testcasesteps= testcasesteps;
    }
 // testcasepass
    public String getTestcasepass() 
    {
    	return testcasepass;
    }
    public void setTestcasepass(String testcasepass)
    {
    	this.testcasepass = testcasepass;
    }
// TestcaseOS
    public String getTestcaseOS()
    {
    	return testcaseOS;
    }	
    public void setTestcaseOS(String testcaseOS) 
    {
    	this.testcaseOS = testcaseOS;
    }
// Not Run
    public boolean isNotrun() 
    {
    	return notrun;
    }	
    public void setNotrun(boolean notrun)
    {
    	this.notrun= notrun;
    }
// Passed 
    public boolean isPassed() 
    {
    	return passed;
    }	
    public void setPassed(boolean passed)
    {
    	this.passed = passed;
    }
 // Passed 
    public boolean isFailed() 
    {
    	return failed;
    }	
    public void setFailed(boolean failed)
    {
    	this.failed = failed;
    }
// Inprogress 
    public boolean isInprogress () 
    {
    	return inprogress;
    }	
    public void setInprogress (boolean inprogress)
    {
    	this.inprogress = inprogress;
    }
// Deferred
    public boolean isDeferred() 
    {
    	return deferred;
    }	
    public void setDeferred(boolean deferred)
    {
    	this.deferred = deferred;
    }
// Blocked
    public boolean isBlocked() 
    {
    	return blocked;
    }	
    public void setBlocked(boolean blocked)
    {
    	this.blocked = blocked;
    }
// User
    public String getUser() {
	return user;
    }

    public void setUser(String user) {
	this.user = user;
    }
}