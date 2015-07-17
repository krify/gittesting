//
//  loginScreen.swift
//  porter
//
//  Created by Manohar Kola on 06/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class loginScreen: UIViewController,UITextFieldDelegate{

    
    @IBOutlet var email: UITextField!
    @IBOutlet var password: UITextField!
    
    var ud = NSUserDefaults.standardUserDefaults()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        email.delegate = self
        password.delegate = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        
        self.view.endEditing(true)
        return true
        
    }
    
    @IBAction func login(sender: AnyObject)
    {
        if(ud.valueForKey("type") as? String == "user")
        {
            ud.setValue(self.email.text, forKey: "email")
            ud.setValue(self.password.text, forKey: "pass")
            var screen: AnyObject? = self.storyboard?.instantiateViewControllerWithIdentifier("userSideMenu")
            self.navigationController?.pushViewController(screen as! UIViewController, animated: true)
        }
        else
        {
            ud.setValue(self.email.text, forKey: "email")
            ud.setValue(self.password.text, forKey: "pass")
            var screen: AnyObject? = self.storyboard?.instantiateViewControllerWithIdentifier("porterSideMenu")
            self.navigationController?.pushViewController(screen as! UIViewController, animated: true)
        }
    }

    @IBAction func register(sender: AnyObject)
    {
        var screen3:UserRegistrationStep1 = self.storyboard?.instantiateViewControllerWithIdentifier("userRegister1") as! UserRegistrationStep1
        self.navigationController?.pushViewController(screen3, animated: true)
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
