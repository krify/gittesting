//
//  PUserMap.swift
//  porter
//
//  Created by Manohar Kola on 06/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class PUserMap: UIViewController {
   
    @IBOutlet var sidebar: UIButton!

    @IBOutlet var email: UILabel!
    @IBOutlet var password: UILabel!

    var ud = NSUserDefaults.standardUserDefaults()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        sidebar.addTarget(self.revealViewController(), action: Selector("revealToggle:"), forControlEvents: UIControlEvents.TouchUpInside)
        self.navigationController?.navigationBarHidden = true
        
        self.email.text = ud.valueForKey("email") as? String
        self.password.text = ud.valueForKey("pass") as? String
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
