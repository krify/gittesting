//
//  porterRegistrationSte2.swift
//  porter
//
//  Created by Manohar Kola on 14/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class porterRegistrationSte2: UIViewController,UITextFieldDelegate{

    @IBOutlet var Scroll: UIScrollView!
    
    @IBOutlet var DriverLisence:UITextField!
    @IBOutlet var Date:UITextField!
    @IBOutlet var month:UITextField!
    @IBOutlet var Year:UITextField!
    @IBOutlet var State:UITextField!
    @IBOutlet var VehicleType:UITextField!
    @IBOutlet var plate:UITextField!
    @IBOutlet var make:UITextField!
    @IBOutlet var Vyear:UITextField!
    @IBOutlet var doors:UITextField!
    
    @IBOutlet var datePicker: UIDatePicker!
    @IBOutlet var topView: UIView!
    @IBOutlet var topImageView: UIImageView!

    
    override func viewDidLoad() {
        super.viewDidLoad()
           Scroll.contentSize = CGSizeMake(Scroll.frame.size.width,1200)
        
        datePicker.addTarget(self, action: Selector("handleDatePicker:"), forControlEvents: UIControlEvents.ValueChanged)
        topView.alpha = 0
        topImageView.layer.cornerRadius = 5
        topImageView.layer.borderColor = UIColor(red: 194/225, green: 194/225, blue: 194/225, alpha: 1.0).CGColor
        topImageView.layer.borderWidth = 2
        
        var tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: Selector("viewTapped"))
        tap.numberOfTapsRequired = 1
        topView.addGestureRecognizer(tap)
        
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
    
    func handleDatePicker(sender: UIDatePicker) {
        
        var dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "dd"
        self.Date.text =  dateFormatter.stringFromDate(sender.date)
        dateFormatter.dateFormat = "MM"
        self.month.text =  dateFormatter.stringFromDate(sender.date)
        dateFormatter.dateFormat = "yyyy"
        self.Year.text =  dateFormatter.stringFromDate(sender.date)
        
    }
    
    @IBAction func expireDateButton(sender: AnyObject)
    {
        self.topView.alpha = 1
    }
    func viewTapped()
    {
        self.topView.alpha = 0
    }
    
    @IBAction func cancle(sender: AnyObject)
    {
        self.navigationController?.popViewControllerAnimated(true)
    }
    @IBAction func next(sender: AnyObject)
    {
        var screen:porterRegistrationStep3 = self.storyboard?.instantiateViewControllerWithIdentifier("porterRegister3") as! porterRegistrationStep3
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
