//
//  EnterNewMobile.swift
//  porter
//
//  Created by Venkata Ramana on 14/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class EnterNewMobile: UIViewController,UITextFieldDelegate {

    @IBOutlet var mobileNumberTextField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
           mobileNumberTextField.delegate=self
        
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

    @IBAction func closeBtnClicked(sender: AnyObject) {
        self.navigationController?.popViewControllerAnimated(true)
    }
    @IBAction func submitBtnClicked(sender: AnyObject) {
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
