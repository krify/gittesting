//
//  CardDetails.swift
//  porter
//
//  Created by Venkata Ramana on 14/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class CardDetails: UIViewController,UITextFieldDelegate {

    @IBOutlet var cardNoTextField: UITextField!
    @IBOutlet var cardNameTextField: UITextField!
    @IBOutlet var expireDateTextField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        expireDateTextField.delegate=self
        cardNoTextField.delegate=self
        cardNameTextField.delegate=self
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
    @IBAction func backAction(sender: AnyObject) {
        self.navigationController?.popViewControllerAnimated(true)
    }

    @IBAction func saveBtnClicked(sender: AnyObject) {
        var screen:UserRegistrationStep3 = self.storyboard?.instantiateViewControllerWithIdentifier("userRegister3") as! UserRegistrationStep3
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
