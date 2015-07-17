//
//  porterRegistrationStep3.swift
//  porter
//
//  Created by Manohar Kola on 15/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class porterRegistrationStep3: UIViewController {

    
    @IBOutlet var Scroll: UIScrollView!
    
    @IBOutlet var Check1:UIButton!
    @IBOutlet var Check2:UIButton!
    @IBOutlet var Check3:UIButton!
    @IBOutlet var Check4:UIButton!
    @IBOutlet var Check5:UIButton!
    @IBOutlet var Check6:UIButton!
    @IBOutlet var Check7:UIButton!
    
    var days = [0,0,0,0,0,0,0]
    
    override func viewDidLoad() {
      
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        Scroll.contentSize = CGSizeMake(Scroll.frame.size.width,1200)

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    @IBAction func NextButton(sender: AnyObject)
    {
        var data:NSMutableArray = []
        for(var i = 0;i<days.count;i++)
        {
            if(days[i] == 1)
            {
                data.addObject(i+1)
            }
        }
        var screen:porterRegistrationStep4 = self.storyboard?.instantiateViewControllerWithIdentifier("porterRegister4") as! porterRegistrationStep4
        screen.days = data
        self.navigationController?.pushViewController(screen, animated: true)
    }
    
    @IBAction func PreviousButton(sender: AnyObject)
    {
        self.navigationController?.popViewControllerAnimated(true)
    }
    
    @IBAction func CheckButton(sender: AnyObject)
    {
        if(sender.tag == 1)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                Check1.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
            }
            else
            {
                days[sender.tag-1] = 0
                Check1.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }
        else if(sender.tag == 2)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                Check2.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
            }
            else
            {
                days[sender.tag-1] = 0
                Check2.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }
        else if(sender.tag == 3)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                Check3.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
            }
            else
            {
                days[sender.tag-1] = 0
                Check3.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }
        else if(sender.tag == 4)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                Check4.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
            }
            else
            {
                days[sender.tag-1] = 0
                Check4.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }
        else if(sender.tag == 5)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                Check5.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
            }
            else
            {
                days[sender.tag-1] = 0
                Check5.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }
        else if(sender.tag == 6)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                Check6.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
            }
            else
            {
                days[sender.tag-1] = 0
                Check6.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }
        else if(sender.tag == 7)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                Check7.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
            }
            else
            {
                days[sender.tag-1] = 0
                Check7.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }
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
