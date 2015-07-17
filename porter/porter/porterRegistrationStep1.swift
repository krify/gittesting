//
//  porterRegistrationStep1.swift
//  porter
//
//  Created by Manohar Kola on 10/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit
import MobileCoreServices

class porterRegistrationStep1: UIViewController,UITextFieldDelegate,UIActionSheetDelegate,UIImagePickerControllerDelegate,UINavigationControllerDelegate {

    
    @IBOutlet var Scroll: UIScrollView!
    @IBOutlet var img: UIImageView!
    @IBOutlet var email: UITextField!
    @IBOutlet var conformEmail: UITextField!
    @IBOutlet var password: UITextField!
    @IBOutlet var firstName: UITextField!
    @IBOutlet var lastName: UITextField!
    @IBOutlet var CurrentWorkingStatus: UITextField!
    @IBOutlet var Address: UITextField!
    @IBOutlet var MobileNumber: UITextField!
    @IBOutlet var Address1: UITextField!
    @IBOutlet var zip: UITextField!
    @IBOutlet var date: UITextField!
    @IBOutlet var month: UITextField!
    @IBOutlet var year: UITextField!
    
    @IBOutlet var image: UIImageView!
    
    
    @IBOutlet var Sview: UIView!
    
    @IBOutlet var datePicker: UIDatePicker!
    @IBOutlet var topView: UIView!
    @IBOutlet var topImageView: UIImageView!
    
    var ud = NSUserDefaults.standardUserDefaults()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        Scroll.contentSize = CGSizeMake(Scroll.frame.size.width,1400)
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
    
    func viewTapped()
    {
        self.topView.alpha = 0
    }

    @IBAction func CancelButton(sender: AnyObject)
    {
        self.navigationController?.popViewControllerAnimated(true)
    }

    @IBAction func NextButton(sender: AnyObject) {
        
//        var screen:porterRegistrationSte2 = self.storyboard?.instantiateViewControllerWithIdentifier("porterRegister2") as! porterRegistrationSte2
//        self.navigationController?.pushViewController(screen, animated: true)
        
        if(self.email.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter Email", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(isValidEmail(self.email.text) == false)
        {
            var alert = UIAlertView(title: nil, message: "Enter Valid Email", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.conformEmail.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter Conform Email", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.conformEmail.text != self.email.text)
        {
            var alert = UIAlertView(title: nil, message: "Email not Matched", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.password.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter Password", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.firstName.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter First Name", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.lastName.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter Last Name", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.CurrentWorkingStatus.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter Current Working Status", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.Address.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter State", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.MobileNumber.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter Mobile Number", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.Address1.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter City", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.zip.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter Zip Code", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else if(self.date.text.isEmpty)
        {
            var alert = UIAlertView(title: nil, message: "Enter Date Of Birth", delegate: nil, cancelButtonTitle: "ok")
            alert.show()
        }
        else{

                if(Reachability.isConnectedToNetwork())
                {
                    let manager = AFHTTPRequestOperationManager()
                    manager.requestSerializer.setValue("ba227a85fbj708f190f934fb9c236f32c", forHTTPHeaderField: "Authorization")
                    manager.responseSerializer = AFJSONResponseSerializer()
                    var DeviceId:String = UIDevice.currentDevice().identifierForVendor.UUIDString
                    var ud = NSUserDefaults.standardUserDefaults()
                    var rparameters: [String: NSString] = ["email":self.email.text,"password":self.password.text,"phone_no":self.MobileNumber.text,"first_name":self.firstName.text,"last_name":self.lastName.text,"work_status":self.CurrentWorkingStatus.text,"city":self.Address1.text,"state":self.Address.text,"zip":self.zip.text,"dob":"\(self.date.text)-\(self.month.text)-\(self.year.text)","device_token":"afsfsdfs","platform":"1","latitude":"16.5656","longitude":"82.1645613510","user_type":"3"]
        
                    println(rparameters)
                    
                    manager.POST("\(API)porter_sign_up",
                        
                        parameters: rparameters,constructingBodyWithBlock: { (formData : AFMultipartFormData!) -> Void in
                            if(ud.valueForKey("img") != nil)
                            {
                            formData.appendPartWithFileData(ud.valueForKey("img") as! NSData, name: "profile_pic", fileName: "image.jpg", mimeType: "image/jpeg")
                            }
                            
                        },
                        
                        success: { (operation: AFHTTPRequestOperation!, responseObject: AnyObject!) in
                            var json = responseObject as! NSDictionary
                            println(json)
                            if(json["status"] as! String == "success")
                            {
                                ud.setValue( json.valueForKey("details")?.valueForKey("step") as! String, forKey: "step")
                                ud.setValue( json.valueForKey("details")?.valueForKey("user_token") as! String, forKey: "token")
                                ud.setValue( json.valueForKey("details")?.valueForKey("verifycode") as! String, forKey: "ver")
                                var alert = UIAlertView(title: nil, message: "registration Done Successfully", delegate: nil, cancelButtonTitle: "ok")
                                alert.show()
                            }
                            
                        },
                        
                        failure: { (operation: AFHTTPRequestOperation!, error: NSError!) in
                            
                            let responce = operation.response as NSHTTPURLResponse
                            println(responce)
                            
                    })
                }

                else
                {
                    var alert = UIAlertView(title: "NO Network", message: "Make Sure you are connected to internet or WIFI", delegate: nil, cancelButtonTitle: "OK")
                    alert.show()
                }
        }
    }
    
    
    @IBAction func DateButton(sender: AnyObject)
    {
        topView.alpha = 1
    }
    
    
    func handleDatePicker(sender: UIDatePicker) {
        
        var dateFormatter = NSDateFormatter()
            dateFormatter.dateFormat = "dd"
            self.date.text =  dateFormatter.stringFromDate(sender.date)
            dateFormatter.dateFormat = "MM"
            self.month.text =  dateFormatter.stringFromDate(sender.date)
            dateFormatter.dateFormat = "yyyy"
            self.year.text =  dateFormatter.stringFromDate(sender.date)
        
    }

    @IBAction func profilePicUpdate(sender: AnyObject) {
        let actionSheet = UIActionSheet(title: "Choose A Picture", delegate: self, cancelButtonTitle: "Cancel", destructiveButtonTitle: nil, otherButtonTitles: "Camera", "Gallery")
        actionSheet.showInView(self.view)
    }
    func actionSheet(actionSheet: UIActionSheet, clickedButtonAtIndex buttonIndex: Int)
    {
        switch buttonIndex{
            
        case 0:
            break;
        case 1:
            fromcamera()
            break;
        case 2:
            fromGallery()
            break;
        case 3:
            
            break;
        default:
            NSLog("Default");
            break;
            
        }
    }
    func fromGallery()
    {
        var picker = UIImagePickerController()
        picker.delegate = self
        picker.sourceType = UIImagePickerControllerSourceType.PhotoLibrary
        picker.mediaTypes =   [kUTTypeImage]
        picker.allowsEditing = true
        self.presentViewController(picker, animated: true, completion: nil)
    }
    func fromcamera()
    {
        if UIImagePickerController.isSourceTypeAvailable(UIImagePickerControllerSourceType.Camera) {
            var picker = UIImagePickerController()
            picker.delegate = self
            picker.sourceType = UIImagePickerControllerSourceType.Camera
            picker.mediaTypes =   [kUTTypeImage]
            picker.allowsEditing = true
            self .presentViewController(picker, animated: true, completion: nil)
        }
        else
        {
            var alert = UIAlertView(title: "Error", message: "No Camera Found", delegate: nil, cancelButtonTitle: "OK")
            alert.show()
        }
        
    }
    // UIImagePicker Delegate Methods
    
    func imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [NSObject : AnyObject])
    {
        var value = info[UIImagePickerControllerMediaType]  as! NSString
        var ud = NSUserDefaults.standardUserDefaults()
        var image:UIImage = info[UIImagePickerControllerEditedImage] as! UIImage
        var imgData:NSData = UIImageJPEGRepresentation(image, 1.0)
        self.image.image = UIImage(data: imgData)
        ud.setObject(imgData, forKey: "img")
        self.image.layer.cornerRadius = 55
        self.image.clipsToBounds = true
        ud.synchronize()
        self.dismissViewControllerAnimated(true, completion: nil)
    }

    func isValidEmail(testStr:String) -> Bool
    {
        let emailRegEx = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9]+\\.[A-Za-z]"
        let range = testStr.rangeOfString(emailRegEx, options:.RegularExpressionSearch)
        let result = range != nil ? true : false
        return result
    }
   
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        self.view.endEditing(true)
        
        var nextTag: NSInteger = textField.tag + 1
        // try to find next responder
        
        let nextResponder = textField.superview?.viewWithTag(nextTag) as UIResponder!
        if nextResponder != nil{
            Scroll.setContentOffset(CGPointMake(0, textField.center.y/2+1), animated: true)// textField.center.y+1
            nextResponder.becomeFirstResponder()
            return true
        }
        else {
            Scroll.setContentOffset(CGPointMake(0, 0), animated: true)
            textField.resignFirstResponder()
            return true
        }
        // return false
    }
    func textFieldDidBeginEditing(textField: UITextField) {
        if (textField == firstName)||(textField == lastName)||(textField == CurrentWorkingStatus)||(textField == Address){
            animateViewMoving(true, moveValue: 150)
        }
    }
    func textFieldDidEndEditing(textField: UITextField) {
        if (textField == firstName)||(textField == lastName)||(textField == CurrentWorkingStatus)||(textField == Address){
            animateViewMoving(false, moveValue: 150)
        }
    }
    
    func animateViewMoving (up:Bool, moveValue :CGFloat)
    {
        var movementDuration:NSTimeInterval = 0.3
        var movement:CGFloat = ( up ? -moveValue : moveValue)
        UIView.beginAnimations( "animateView", context: nil)
        UIView.setAnimationBeginsFromCurrentState(true)
        UIView.setAnimationDuration(movementDuration )
        //        self.view.frame = CGRectOffset(self.view.frame, 0,  movement)
        self.Sview.frame = CGRectOffset(self.Sview.frame, 0, movement)
        UIView.commitAnimations()
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
