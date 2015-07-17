//
//  UserRegistrationStep2.swift
//  porter
//
//  Created by Manohar Kola on 13/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit
import MobileCoreServices

class UserRegistrationStep2: UIViewController,UIActionSheetDelegate,UIImagePickerControllerDelegate,UINavigationControllerDelegate,UITextFieldDelegate{

    var ud = NSUserDefaults.standardUserDefaults()
    
    @IBOutlet var pImage: UIImageView!
    @IBOutlet var PUserView: UIView!
    @IBOutlet var BUserView: UIView!
    
    @IBOutlet var PFirstName:UITextField!
    @IBOutlet var PLastName:UITextField!
    
    @IBOutlet var Bimage: UIImageView!
    @IBOutlet var BName:UITextField!
    @IBOutlet var BPhone:UITextField!
    @IBOutlet var BAddress:UITextField!
    @IBOutlet var BCity:UITextField!
    @IBOutlet var BState:UITextField!
    @IBOutlet var BZip:UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if(ud.valueForKey("usertype") as! String == "p")
        {
            self.PUserView.alpha = 1
            self.BUserView.alpha = 0
        }
        else
        {
            self.PUserView.alpha = 0
            self.BUserView.alpha = 1
        }
        
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
    
    
    @IBAction func BPicEdit(sender: AnyObject)
    {
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
        if(ud.valueForKey("usertype") as! String == "p")
        {
            self.pImage.image = UIImage(data: imgData)
        }
        else
        {
        self.Bimage.image = UIImage(data: imgData)
        }
        ud.synchronize()
        self.dismissViewControllerAnimated(true, completion: nil)
    }
    
    @IBAction func nextBtnClicked(sender: AnyObject) {
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
