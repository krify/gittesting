//
//  type.swift
//  porter
//
//  Created by Manohar Kola on 06/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class type: UIViewController {

    var ud = NSUserDefaults.standardUserDefaults()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    @IBAction func user(sender: AnyObject)
    {
        ud.setValue("user", forKey: "type")
        var screen2:LoginInOrRegister = self.storyboard?.instantiateViewControllerWithIdentifier("screen2") as! LoginInOrRegister
        self.navigationController?.pushViewController(screen2, animated: true)
    }
    @IBAction func porter(sender: AnyObject)
    {
        ud.setValue("porter", forKey: "type")
        var screen2:LoginInOrRegister = self.storyboard?.instantiateViewControllerWithIdentifier("screen2") as! LoginInOrRegister
        self.navigationController?.pushViewController(screen2, animated: true)
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
