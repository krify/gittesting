//
//  MobileVerification.swift
//  porter
//
//  Created by Venkata Ramana on 14/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class MobileVerification: UIViewController {

    @IBOutlet var AlertView: UIView!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func nextBtnClicked(sender: AnyObject) {
/* var screen:UserRegistrationStep2 = self.storyboard?.instantiateViewControllerWithIdentifier("userRegister2") as! UserRegistrationStep2
self.navigationController?.pushViewController(screen, animated: true)*/
    }

    @IBAction func cancelBtnClicked(sender: AnyObject) {
         self.navigationController?.popViewControllerAnimated(true)
    }
    @IBAction func alertOkBtnClicked(sender: AnyObject) {
        AlertView.alpha=0
    }
    @IBAction func changeMobilephoneBtnClicked(sender: AnyObject) {
        var screen:EnterNewMobile = self.storyboard?.instantiateViewControllerWithIdentifier("newmobile") as! EnterNewMobile
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
