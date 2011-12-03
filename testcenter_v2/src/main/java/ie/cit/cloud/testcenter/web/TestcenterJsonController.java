package ie.cit.cloud.testcenter.web;


import ie.cit.cloud.testcenter.TestCaseService;
import ie.cit.cloud.testcenter.model.TestCase;
import ie.cit.cloud.testcenter.TestPlanService;
import ie.cit.cloud.testcenter.model.TestPlan;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;


@Controller
@RequestMapping("testconfig")
public class TestcenterJsonController {
    @Autowired
    private TestCaseService testcaseService;
    @Autowired
    private TestPlanService testplanService;
    
// test cases 
    @RequestMapping("testcases")
    @ResponseBody
    public Collection<TestCase> index() { 
    	return testcaseService.getAllTestCases();  
    }

    @RequestMapping(value = "/testcases/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody TestCase getTestCaseAt(@PathVariable("index") Long id) {
    	return testcaseService.getTestCase(id);
    }

    @RequestMapping(value = "/testcases", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTestCase(@RequestBody TestCase testcase, HttpServletRequest request,HttpServletResponse response) {
    	testcaseService.getAllTestCases(); 	
    	response.addHeader("Location",getLocationForChildResource(request, testcase.getId()));
    }

    @RequestMapping(value = "/testcases/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTestCaseAt(@RequestBody TestCase testcase, @PathVariable("id") Long id) {
    	testcaseService.updateTestCaseWithId(id, testcase);
    }

    @RequestMapping(value = "/testcases/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestCaseAt(@PathVariable("id") Long id) {
    	testcaseService.remove(id);
    }
// per testplan
    @RequestMapping(value = "{index}/testcases", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Collection<TestCase> getTestCaseAt2(@PathVariable("index") Long id) {	
    	return testcaseService.getAllTestCasesByID(id,testplanService.getTestPlan(id));
    }
 //tesplans 
    @RequestMapping("testplans")
    @ResponseBody
    public Collection<TestPlan> index1() {
    	return testplanService.getAllTestPlans();
    }

    @RequestMapping(value = "/testplans/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody TestPlan getTestPlanAt(@PathVariable("index") Long id) {
    	return testplanService.getTestPlan(id);
    }

    @RequestMapping(value = "/testplans", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTestPlan(@RequestBody TestPlan testplan, HttpServletRequest request,
	    HttpServletResponse response) {
    	testplanService.addNewTestPlan(testplan);
	response.addHeader("Location",
		getLocationForChildResource(request, testplan.getId()));
    }

    @RequestMapping(value = "/testplans/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTestPlanAt(@RequestBody TestPlan testplan, @PathVariable("id") Long id) {
    	testplanService.updateTestPlanWithId(id, testplan);
    }

    @RequestMapping(value = "/testplans/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTestPlanAt(@PathVariable("id") Long id) {
    	testplanService.remove(id);
    	testcaseService.removeAllTestWithID(id);
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }

    private String getLocationForChildResource(HttpServletRequest request,
	    Object childIdentifier) {
	// get the current URL from the reguest
	final StringBuffer url = request.getRequestURL();
	// append the /xyz to the URL and make it a UriTemplate
	final UriTemplate template = new UriTemplate(url.append("/{childId}")
		.toString());
	return template.expand(childIdentifier).toASCIIString();
    }
}
