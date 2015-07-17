//
//  porterprofileViewController.swift
//  porter
//
//  Created by Moulika Attili on 15/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class porterprofileViewController: UIViewController {

    @IBOutlet var datepicker: UIDatePicker!
    @IBOutlet var year: UITextField!
    @IBOutlet var month: UITextField!
    @IBOutlet var day: UITextField!
    @IBOutlet var bgimagedate: UIImageView!
    @IBOutlet var dateview: UIView!
    @IBOutlet var bgimage: UIImageView!
    @IBOutlet var emailview: UIView!
    @IBOutlet var scrollbar: UIScrollView!
    @IBOutlet var label2: UILabel!
    @IBOutlet var label1: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        scrollbar.contentSize=CGSizeMake(scrollbar.frame.size.width,750)

        scrollbar.contentSize = CGSizeMake(scrollbar.frame.size.width, 2200)
        var tapgesture:UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: "tapon")
        tapgesture.numberOfTapsRequired = 1
        tapgesture.numberOfTouchesRequired = 1
        bgimagedate.addGestureRecognizer(tapgesture)
        fontseetings()
        // Do any additional setup after loading the view.
    }
    func tapon(){
        bgimagedate.alpha = 0.0
        dateview.alpha = 0.0
        var date = datepicker.date
        var dateformatter = NSDateFormatter()
        dateformatter.dateFormat = "yyyy"
        var yeardate:String!
        var monthdate:String!
        var daydate:String!
        yeardate = dateformatter.stringFromDate(date)
        year.text = yeardate
        dateformatter.dateFormat = "MM"
        monthdate = dateformatter.stringFromDate(date)
        month.text = monthdate
        dateformatter.dateFormat = "dd"
        daydate = dateformatter.stringFromDate(date)
        day.text = daydate
        
    }
    override func viewWillAppear(animated: Bool) {
        bgimage.alpha = 0.0
        emailview.alpha = 0.0
        bgimagedate.alpha = 0.0
        dateview.alpha = 0.0
    }
    
    @IBAction func yearbutton(sender: AnyObject) {
        bgimagedate.alpha = 0.9
        dateview.alpha = 1.0
    }
    @IBAction func monthbutton(sender: AnyObject) {
        bgimagedate.alpha = 0.9
        dateview.alpha = 1.0
    }
    @IBAction func datebutton(sender: AnyObject) {
        bgimagedate.alpha = 0.9
        dateview.alpha = 1.0
    }
    @IBAction func edit(sender: AnyObject) {
        bgimage.alpha = 0.7
        emailview.alpha = 1.0
    }
    
    @IBAction func SAVE(sender: AnyObject) {
    }
    
    @IBAction func signout(sender: AnyObject) {
    }
    
    @IBAction func cancel(sender: AnyObject) {
        bgimage.alpha = 0.0
        emailview.alpha = 0.0
    }
    @IBAction func submit(sender: AnyObject) {
        bgimage.alpha = 0.0
        emailview.alpha = 0.0
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func fontseetings() {
        println(UIFont.fontNamesForFamilyName("Open Sans"))
      label1.font = UIFont(name: "OpenSansLight-Italic", size: 16)
      label2.font = UIFont(name: "OpenSansLight-Italic", size: 16)
    }
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return false
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
