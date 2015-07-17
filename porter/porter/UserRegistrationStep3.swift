//
//  UserRegistrationStep3.swift
//  porter
//
//  Created by Venkata Ramana on 14/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class UserRegistrationStep3: UIViewController,UITextFieldDelegate {

    @IBOutlet var CvvTextField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        CvvTextField.delegate=self
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        
        self.view.endEditing(true)
        return true
        
    }
    @IBAction func takecardDetails(sender: AnyObject) {
        var screen:CardDetails = self.storyboard?.instantiateViewControllerWithIdentifier("cardscreen") as! CardDetails
        self.navigationController?.pushViewController(screen, animated: true)
    }

    @IBAction func PreviousBtnClicked(sender: AnyObject) {
         self.navigationController?.popViewControllerAnimated(true)
    }
    
    @IBAction func nextBtnClicked(sender: AnyObject) {
        var screen:MobileVerification = self.storyboard?.instantiateViewControllerWithIdentifier("verifymobile") as! MobileVerification
        self.navigationController?.pushViewController(screen, animated: true)
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
