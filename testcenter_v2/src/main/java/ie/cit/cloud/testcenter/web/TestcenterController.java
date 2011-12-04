/**
 * 
 */
package ie.cit.cloud.testcenter.web;

/**
 * @author byrnek1
 *
 */

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import java.util.Collection;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import ie.cit.cloud.testcenter.TestCaseService;
import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.TestPlanService;
import ie.cit.cloud.testcenter.model.TestPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class TestcenterController {
    @Autowired
    private TestCaseService testcaseService;
    @Autowired
    private TestPlanService testplanService;   

	// index()
	// Opens the main testcenter page with the login details
	// Includes error message if one sent	
    @RequestMapping(value = { "index", ""}, method = GET)
    public String index(@RequestParam(required = false) String errormessage,Model model) {	
    	model.addAttribute("errormessage", errormessage);		
    	return "index";
    }    
    
    // accessDenied()
 	// Opens the accessdenied page with the login details if a user try to access a page
 	// they dont have access to
    @RequestMapping(value = { "testconfig/accessdenied"}, method = GET)
    public String accessDenied(@RequestParam(required = false) String errormessage,Model model) {	
    	model.addAttribute("errormessage", errormessage);		
    	return "index";
    }    
    
    // newtestplan()
 	// Opens the newtestplan page where the admin user can enter a new testt plan
 	// Includes both error and success message if one sent
    @RequestMapping(value = { "testconfig/newtestplan"}, method = GET)
    public String newtestplan(@RequestParam(required = false) String errormessage,String successmessage,Model model) {	
    	model.addAttribute("errormessage", errormessage);
    	return "testconfig/newtestplan";
    }   
    
    // createNewTestPlan()
 	// Checks if a test plan with the same name exists
 	// if no result found: add new testplan to the database
    // If test plan exists then redirect with error message
    @RequestMapping(value = { "testconfig/newtestplan"}, method = POST)   
    public String createNewTestPlan(@RequestParam String testplanName,String testername, Model model) {  
    	try{
    		// Test plan already exists    		
    		System.out.println("here ---: "+testplanService.getTestPlanByName(testplanName));    	
    		return "redirect:newtestplan.html?errormessage="+testplanName+" already exists";	 
    	}
    	catch(NoResultException nre)
    	{
    		// No Test Plans of this name Exist
    		try{
	    		testplanService.addNewTestPlan(new TestPlan(testplanName,testername));
	    		return "redirect:../viewtests/viewtestplan.html";    
    		}
    		catch(ConstraintViolationException CVE)
    		{   			
    			System.out.println("ConstraintViolations - : "+CVE.getConstraintViolations());
    			
    			return "redirect:newtestplan.html?errormessage=Testplan name must be between 2 to 32 characters";	
    		}
    	}
    
    } 
    
    // editTestPlan()
 	// Checks if the test plan with testplanID exists
 	// if no result found: redirect to viewtestplan with error message
    // If a test plan exists open edittestplan with all that testplan details 
    @RequestMapping(value = { "testconfig/edittestplan"}, method = GET)   
    public String editTestPlan(@RequestParam(required = false) String errormessage,String successmessage,Long testplanID, Model model) {  
    	try{
    		// Test plan exists    
    		model.addAttribute("errormessage", errormessage);
    		model.addAttribute("successmessage", successmessage);
    		model.addAttribute("testplanID", testplanID);
    		model.addAttribute("testplanName", testplanService.getTestPlan(testplanID).getTestplanName());
        	model.addAttribute("testplanTester", testplanService.getTestPlan(testplanID).getTesterName());	     	
	    	return "testconfig/edittestplan"; 
    	}
    	catch(NoResultException nre)
    	{
    		return "redirect:../viewtests/viewtestplan.html?errormessage=No Test Plan to Edit";	    		
    	}    
    }  
    
    // updateTestPlan()
 	// Update Test plan with new name 	
    @RequestMapping(value = { "testconfig/edittestplan"}, method = POST)   
    public String updateTestPlan(@RequestParam Long testplanID,String testplanName, Model model) {    
    	testplanService.updateTestPlanNameWithId(testplanID, testplanService.getTestPlan(testplanID), testplanName);
    	return "redirect:../viewtests/viewtestplan.html?successmessage="+testplanService.getTestPlan(testplanID).getTestplanName()+" Updated to " +testplanName;	 
    }  

    // viewAllTestPlans()
 	// collects list of testplans
    // if none exist : redirect to newtestplan with error message
    // if at least one exists then display those details
    @RequestMapping(value = { "viewtests/viewtestplan"}, method = GET)
    public String viewAllTestPlans(@RequestParam(required = false) String errormessage,String successmessage,Model model) {
    	Collection<TestPlan> AllTestPlans = testplanService.getAllTestPlans();
    	if (AllTestPlans.isEmpty())
    	{
    		// No Test Plans Exist    			
    		return "redirect:../testconfig/newtestplan.html?errormessage=No Test Plans exist";        	
    	}
    	else
    	{
    		
    		model.addAttribute("errormessage", errormessage);
    		model.addAttribute("successmessage", successmessage);
	    	model.addAttribute("testplans", testplanService.getAllTestPlans());    	
	    	return "viewtests/viewtestplan";
    	}
    }  
    
    // deleteTestPlan()
 	// Deletes testplan and redirects to viewtestplans    
    @RequestMapping(value = { "testconfig/deletetestplan"}, method = GET)    
    public String deleteTestPlan(@RequestParam Long testplanID, Model model) {
    	String TempTestPlanName = testplanService.getTestPlan(testplanID).getTestplanName();    	
    	testplanService.remove(testplanID);	
    	testcaseService.removeAllTestWithID(testplanID);	
    	return "redirect:../viewtests/viewtestplan.html?errormessage="+TempTestPlanName+" including all testcases were deleted";
     }
    
    // newTestCase()
 	// if new test case has an associated testplan then open newtestcase
    // if no test plan giving redirect with error message
    @RequestMapping(value = { "testconfig/newtestcase"}, method = GET)
    public String newTestCase(@RequestParam(required = false) String errormessage,String successmessage,Long testplanID,Model model) {
    	if (testplanID == null)
    	{
    		// No Test Plan selected
    		return "redirect:../viewtests/viewtestplan.html?errormessage=You Must select a plan";	
    	}
    	else
    	{
    		try{
	    		model.addAttribute("errormessage", errormessage);
	    		model.addAttribute("successmessage", successmessage);
	    		model.addAttribute("testplanID", testplanID);
	    		model.addAttribute("testplanName", testplanService.getTestPlan(testplanID).getTestplanName());
	        	model.addAttribute("testplanTester", testplanService.getTestPlan(testplanID).getTesterName());	
	    		return "testconfig/newtestcase";
    		}
    		catch(NoResultException NRE)
    		{
    			return "redirect:../viewtests/viewtestplan.html?errormessage=Test Plan Does Not exist";	
    		}
    	}    	
    }   
    
    // createNewTestCase()
  	// Check if test case with entered name exists
    // if not then create new test
    // if there is an existing test case with that name redirect to newtestcase with error message    
    @RequestMapping(value = { "testconfig/newtestcase"}, method = POST)   
    public String createNewTestCase(@RequestParam Long testplanID,String testcasename,String testcasesummary,String testcasepre,String testcasesteps,String testcasepass,String testcaseOS, Model model) {    		
    	if (testcaseService.getAllTestCasesByName(testplanID,testcasename).isEmpty())
    	{
    		// No Test case of this name Exists
    		try{
	    		testcaseService.addNewTestCase(new TestCase(testplanService.getTestPlan(testplanID),testplanID,testcasename,testcasesummary,testcasepre,testcasesteps,testcasepass,testcaseOS), testplanService.getTestPlan(testplanID));
	    		if (testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID)))
	    		{
	    			return "redirect:enterresults.html?testplanID="+testplanID+"&successmessage="+testcasename+" added";
	    		}
	    		else
	    		{
	    			return "redirect:enterresults.html?errormessage="+testcasename+" could not be updated&testplanID="+testplanID;
	    		}
    		}
    		catch(ConstraintViolationException CVE)
    		{
    			return "redirect:newtestcase.html?testplanID="+testplanID+"&errormessage=Testcase name must be between 2 to 254 characters";	
    		}
    	}
    	else
    	{
    		// Test case already exists
    		System.out.println("here ---: "+testcaseService.getAllTestCasesByName(testplanID,testcasename));    	
    		return "redirect:newtestcase.html?testplanID="+testplanID+"&errormessage="+testcasename+" already exists";	
    				   	
    	}
    	
    } 

    // editTestCase()
  	// Checks if the test case with testplanID and testcaseID exists
  	// if no result found: redirect to enterresults with error message
    // If a test plan exists open edittestcase with all that testcase details 
    @RequestMapping(value = { "testconfig/edittestcase"}, method = GET)   
    public String editTestCase (@RequestParam(required = false) String errormessage,String successmessage,Long testplanID,Long testcaseID, Model model) {  
    	try{
    		// Test plan exists     		
    		model.addAttribute("errormessage", errormessage);
    		model.addAttribute("successmessage", successmessage);
    		model.addAttribute("testplan",  testplanService.getTestPlan(testplanID));	
    		model.addAttribute("testplans", testplanService.getAllTestPlans()); 
    		model.addAttribute("testcase", testcaseService.getTestCase(testcaseID)); 
	    	return "testconfig/edittestcase"; 
    	}
    	catch(NoResultException nre)
    	{
    		return "redirect:enterresults.html?testplanID="+testplanID+"&errormessage=No Test Plan to Edit";	    		
    	}    
    }  
    
    // updateTestCase()
  	// if testplanID_changed is equal to testplanID : update test case with testplanID and testcaseID
    // if testplanID_changed is NOT equal to testplanID : Update test case with NEW testplanID and testcaseID
    @RequestMapping(value = { "testconfig/edittestcase"}, method = POST)   
    public String updateTestCase(@RequestParam Long testplanID,Long testplanID_changed,Long testcaseID,String testcasename,String testcasesummary,String testcasepre,String testcasesteps,String testcasepass,String testcaseOS, Model model)
    {   
    	testcaseService.updateTestCaseDetails(testplanID_changed,testplanService.getTestPlan(testplanID_changed).getTestplanName(),testplanService.getTestPlan(testplanID_changed),
    	testcaseID, testcasename,testcasesummary,testcasepre,testcasesteps,testcasepass,testcaseOS,testcaseService.getTestCase(testcaseID));
    	if (testplanID_changed != testplanID)
    	{
    		// Test case Was moved to new test plan so an update of test results need to be implemented
    		if (testcaseService.getAllTestCasesByName(testplanID_changed,testcasename).isEmpty())
        	{    
    			// No Test case of this name Exists
    			testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
    			testplanService.updateTestPlan(testplanID_changed,testplanService.getTestPlan(testplanID_changed));
    			return "redirect:enterresults.html?testplanID="+testplanID_changed+"&successmessage="+testcasename+" Updated";	    		    
        	}
    		else
    		{
    			return "redirect:edittestcase.html?testplanID="+testplanID+"&testcaseID="+testcaseID+"&errormessage=Cannot Move "+testcasename+" To "+ testplanService.getTestPlan(testplanID_changed).getTestplanName()+" because a Test case with the same name already exists";	 
    		}
    	}
    	else
    	{
    		return "redirect:enterresults.html?testplanID="+testplanID_changed+"&successmessage="+testcasename+" Updated";	 
    	}
    	
    }     
    
    // enterResults()
    // if new test cases has an associated testplan then open enterresults with all the test cases
    // if no test plan giving redirect with error message    
    @RequestMapping(value = { "testconfig/enterresults" }, method = GET) 
    public String enterResults(@RequestParam(required = false) Long testplanID,String errormessage,String successmessage,Model model) {
    	if (testplanID == null)
    	{
    		// No Test Plan selected
    		return "redirect:../viewtests/viewtestplan.html?errormessage=You Must select a plan";	
    	}
    	else
    	{
	    	try{
	    		Collection<TestCase> AllTestCases = testcaseService.getAllTestCasesByID(testplanID,testplanService.getTestPlan(testplanID));
	    		if (AllTestCases.size() <=0)
	        	{
	        		return "redirect:newtestcase.html?testplanID="+testplanID+"&errormessage=No Test cases Exist";
	        	}
	        	else
	        	{
	        		model.addAttribute("errormessage", errormessage);
		    		model.addAttribute("successmessage", successmessage);
		    		model.addAttribute("testcases", testcaseService.getAllTestCasesByID(testplanID,testplanService.getTestPlan(testplanID)));
		        	model.addAttribute("testplan",  testplanService.getTestPlan(testplanID));			        	
		        	return "testconfig/enterresults";
	        	}
	    	}
	    	catch (NoResultException e)
	    	{
	    		// No Test Cases Exist
	    		return "redirect:newtestcase.html?testplanID="+testplanID+"&errormessage=You must create a new test case";	    		
	    	}    
    	}    	
    }       
    
    // deleteTestCase()
    // delete Test case with testcaseID and testplanID
    // Update Test plan results
    @RequestMapping(value = "testconfig/deletetestcase", method = GET)
    public String deleteTestCase(@RequestParam Long testcaseID,Long testplanID, Model model) {
		testcaseService.remove(testcaseID);	
		testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    
    // notrun()
    // update testcase state to only Not Run = true
    // Update Test plan results 
    @RequestMapping(value = "testconfig/notrun", method = GET)
    public String notrun(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.notrun(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
    	return "redirect:enterresults.html?testplanID="+testplanID;
    }
    
    // passed()
    // update testcase state to only passed = true
    // Update Test plan results 
    @RequestMapping(value = "testconfig/passed", method = GET)
    public String passed(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.passed(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    
    // failed()
    // update testcase state to only failed = true
    // Update Test plan results 
    @RequestMapping(value = "testconfig/failed", method = GET)
    public String failed(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.failed(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    
    // inprogress()
    // update testcase state to only inprogress = true
    // Update Test plan results 
    @RequestMapping(value = "testconfig/inprogress", method = GET)
    public String inprogress(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.inprogress(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    
    // deferred()
    // update testcase state to only deferred = true
    // Update Test plan results 
    @RequestMapping(value = "testconfig/deferred", method = GET)
    public String deferred(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.deferred(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    	
    // blocked()
    // update testcase state to only blocked = true
    // Update Test plan results 
    @RequestMapping(value = "testconfig/blocked", method = GET)
    public String blocked(@RequestParam Long id,Long testplanID, Model model) {
    	testcaseService.blocked(testcaseService.getTestCase(id));
    	testplanService.updateTestPlan(testplanID,testplanService.getTestPlan(testplanID));
		return "redirect:enterresults.html?testplanID="+testplanID;
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }

}