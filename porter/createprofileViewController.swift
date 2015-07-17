//
//  createprofileViewController.swift
//  porter
//
//  Created by Moulika Attili on 15/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class createprofileViewController: UIViewController,UITextFieldDelegate{
    @IBOutlet var scrollbar: UIScrollView!
    @IBOutlet var emailview: UIView!
    @IBOutlet var bgimage: UIImageView!
    
    @IBOutlet var businessview: UIView!
    
    @IBOutlet var notifyview: UIView!
    @IBOutlet var userview: UIView!
    override func viewDidLoad() {
        super.viewDidLoad()
        scrollbar.contentSize=CGSizeMake(scrollbar.frame.size.width,750)
         println(UIFont.fontNamesForFamilyName("Open Sans"))
        // Do any additional setup after loading the view.
    }
   
    @IBAction func back(sender: AnyObject) {
    self.navigationController?.popToRootViewControllerAnimated(true)
    }
    @IBAction func edit(sender: AnyObject) {
        bgimage.alpha = 0.7
        emailview.alpha = 1.0
    }
    override func viewWillAppear(animated: Bool) {
        bgimage.alpha = 0.0
        emailview.alpha = 0.0
        businessview.alpha = 0.0
        userview.frame = CGRectMake(5, 233, 309, 239)
        //userview.frame = CGRectMake(5, 531, 309, 239)
        notifyview.frame = CGRectMake(8, 468, 304, 72)
        //notifyview.frame = CGRectMake(8, 714, 304, 72)
    }
    
    @IBAction func submit(sender: AnyObject) {
        bgimage.alpha = 0.0
        emailview.alpha = 0.0
    }
    @IBAction func cancel(sender: AnyObject) {
        bgimage.alpha = 0.0
        emailview.alpha = 0.0
    }
    
    func textFieldDidBeginEditing(textField: UITextField) {
        self.view.frame.origin.y -= 80

    }
    func textFieldDidEndEditing(textField: UITextField) {
        self.view.frame.origin.y += 80

    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return false
    }


}
