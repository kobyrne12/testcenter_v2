/**
 * 
 */
package ie.cit.cloud.testcenter.model;

/**
 * @author byrnek1
 *
 */

import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.swing.text.Document;
import javax.validation.constraints.Pattern;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


@Entity(name = "TestPlan")
public class TestPlan {

	@Id        
	@GeneratedValue
	@Column(name = "testplanID")
    private Long id;
	
	@Length(min = 2, max = 32, message = "Testplan name must be between 2 to 32 characters.")
	@NotEmpty(message = "TestPlan Name is required.")
	private String testplanName;  	

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Collection<TestCase> testcases;      
    
    @NotEmpty(message = "Tester Name is required.")
    private String testername;    
    @Basic    
    private int totalTests;
    @Basic    
    private int totalNotRun;
    @Basic    
    private int totalPassed;
    @Basic    
    private int totalFailed;
    @Basic    
    private int totalInProgress;
    @Basic    
    private int totalDeferred;
    @Basic    
    private int totalBlocked;
    
//  @Basic
//  private String user; 
    
// @Pattern(regexp = "^\\D*$", message = "Middle initial must not contain numeric characters.")
    
    public TestPlan() {	
    }
    
    public TestPlan(String testplanname,String testername) {
    	this(testplanname,testername,0,0,0,0,0,0,0);
    }

    public TestPlan(String testplanName, String testername ,int totalTests,int totalNotRun, int totalPassed,int totalFailed,int totalInProgress,int totalDeferred, int totalBlocked) {
    	this.testplanName = testplanName;
    	this.testername = testername;		
		this.totalTests = totalTests;
		this.totalNotRun = totalNotRun;
		this.totalPassed = totalPassed;
		this.totalFailed = totalFailed;
		this.totalInProgress = totalInProgress;
		this.totalDeferred = totalDeferred;
		this.totalBlocked = totalBlocked;
    }
    
//All Test cases       
    public Collection<TestCase> getTestCases() 
    {
    	return testcases;
    }
    public void setTestCases(Collection<TestCase> Alltestcases)
    {
    	testcases = Alltestcases;
    }
 // ID 
    public Long getId() {
    	return id;
    }
 // TestplanName
    public String getTestplanName()
    {
    	return testplanName;
    }	
    public void setTestplanName(String testplanName) 
    {
    	this.testplanName = testplanName;
    }
// Testername
    public String getTesterName()
    {
    	return testername;
    }	
    public void setTesterName(String testername) 
    {
    	this.testername = testername;
    }
 // TotalTests
    public int getTotalTests()
    {
    	return totalTests;
    }	
    public void setTotalTests(int totalTests) 
    {
    	this.totalTests = totalTests;
    }
// totalnotrun
    public int getTotalNotRun()
    {
    	return totalNotRun;
    }	
    public void setTotalNotRun(int totalNotRun) 
    {
    	this.totalNotRun = totalNotRun;
    }
 // totalpassed
    public int getTotalPassed()
    {
    	return totalPassed;
    }	
    public void setTotalPassed(int totalPassed) 
    {
    	this.totalPassed = totalPassed;
    }
 // totalfailed
    public int getTotalFailed()
    {
    	return totalFailed;
    }	
    public void setTotalFailed(int totalFailed) 
    {
    	this.totalFailed = totalFailed;
    }
 // totalinprogress
    public int getTotalInProgress()
    {
    	return totalInProgress;
    }	
    public void setTotalInProgress(int totalInProgress) 
    {
    	this.totalInProgress = totalInProgress;
    }
 // totaldeferred
    public int getTotaldeferred()
    {
    	return totalDeferred;
    }	
    public void setTotalDeferred(int totalDeferred) 
    {
    	this.totalDeferred = totalDeferred;
    }
 // totalblocked
    public int getTotalBlocked()
    {
    	return totalBlocked;
    }	
    public void setTotalBlocked(int totalBlocked) 
    {
    	this.totalBlocked = totalBlocked;
    }

// User
//    public String getUser() {
//	return user;
 //   }

//   public void setUser(String user) {
//	this.user = user;
//    }
}