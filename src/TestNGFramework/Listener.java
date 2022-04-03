package TestNGFramework;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/*
* Listener la 1 annotation giup
* bat cac su kien khi testcase fail, success hay bi skip
* Su dung chu yeu de bat khi test case bi fail, luc do
* ta co the tao 1 file HTML report hoac chup anh luc bi fail
* hoac khoi tao 1 ticket bao cao tren Jira
* De khoi tao Listener cho 1 class cu the ta su dung @Listeners({ten_package.ten_class.class})
* De khoi tao Listener cho toan bo class ta khai bao trong file .xml duoi dang
* <listeners><listener class-name="ten_class">
* */
public class Listener implements ITestListener {


    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test case fails");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
