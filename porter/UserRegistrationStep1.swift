//
//  UserRegistrationStep1.swift
//  porter
//
//  Created by Manohar Kola on 07/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class UserRegistrationStep1: UIViewController,UITextFieldDelegate {

    @IBOutlet var PandBView: UIView!
    @IBOutlet var PSelectionView: UIView!
    @IBOutlet var BSelectionView: UIView!
    @IBOutlet var Pview: UIView!
    @IBOutlet var BView: UIView!
    
    @IBOutlet var PCheckImage:UIImageView!
    @IBOutlet var BCheckImage:UIImageView!
    
    @IBOutlet var PEmail:UITextField!
    @IBOutlet var PMobileNumber:UITextField!
    @IBOutlet var PPassword:UITextField!
    
    @IBOutlet var BEmail:UITextField!
    @IBOutlet var BConformEmail:UITextField!
    @IBOutlet var BMobileNumber:UITextField!
    @IBOutlet var BPassword:UITextField!
    
    
    var ud = NSUserDefaults.standardUserDefaults()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        PandBView.backgroundColor = UIColor.clearColor()
        PandBView.layer.borderWidth = 2
        PandBView.layer.cornerRadius = 1
        PandBView.layer.borderColor = UIColor(red: 194/225, green: 194/225, blue: 194/225, alpha: 1.0).CGColor
        PandBView.clipsToBounds = true
        self.PSelectionView.backgroundColor = UIColor(red: 194/225, green: 194/225, blue: 194/225, alpha: 1.0)
        self.BSelectionView.backgroundColor = UIColor.clearColor()
        ud.setValue("p", forKey: "usertype")
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
    
    @IBAction func PUserAction(sender: AnyObject)
    {
        self.PSelectionView.backgroundColor = UIColor(red: 194/225, green: 194/225, blue: 194/225, alpha: 1.0)
        self.PCheckImage.image = UIImage(named: "check-green.png")
        self.BCheckImage.image = UIImage(named: "un-check-green.png")
        self.BSelectionView.backgroundColor = UIColor.clearColor()
        self.Pview.alpha = 1
        self.BView.alpha = 0
        ud.setValue("p", forKey: "usertype")
    }
    
    @IBAction func BUserAction(sender: AnyObject)
    {
        self.BSelectionView.backgroundColor = UIColor(red: 194/225, green: 194/225, blue: 194/225, alpha: 1.0)
        self.BCheckImage.image = UIImage(named: "check-green.png")
        self.PCheckImage.image = UIImage(named: "un-check-green.png")
        self.PSelectionView.backgroundColor = UIColor.clearColor()
        self.Pview.alpha = 0
        self.BView.alpha = 1
        ud.setValue("b", forKey: "usertype")
    }
    
    @IBAction func cancel(sender: AnyObject)
    {
        self.navigationController?.popViewControllerAnimated(true)
    }
    @IBAction func PNext(sender: AnyObject)
    {
        var screen:UserRegistrationStep2 = self.storyboard?.instantiateViewControllerWithIdentifier("userRegister2") as! UserRegistrationStep2
        self.navigationController?.pushViewController(screen, animated: true)
    }

    @IBAction func BNext(sender: AnyObject)
    {
        var screen:UserRegistrationStep2 = self.storyboard?.instantiateViewControllerWithIdentifier("userRegister2") as! UserRegistrationStep2
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
