//
//  porterRegistrationStep4.swift
//  porter
//
//  Created by Manohar Kola on 15/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class porterRegistrationStep4: UIViewController,UITableViewDelegate,UITableViewDataSource {

    var days:NSMutableArray!
    
    var day1 = [0,0,0,0,0,0]
    var day2 = [0,0,0,0,0,0]
    var day3 = [0,0,0,0,0,0]
    var day4 = [0,0,0,0,0,0]
    var day5 = [0,0,0,0,0,0]
    var day6 = [0,0,0,0,0,0]
    var day7 = [0,0,0,0,0,0]
    
    @IBOutlet var tableView: UITableView!
    var open:Bool = false
    var selected:Int = -1
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tableView.dataSource = self
        self.tableView.delegate = self
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.days.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        var cell:porterStep4cell = tableView.dequeueReusableCellWithIdentifier("cell", forIndexPath: indexPath) as! porterStep4cell
        cell.selectionStyle = UITableViewCellSelectionStyle.None
        cell.backgroundColor = UIColor.clearColor()
        if(days.objectAtIndex(indexPath.row) as! Int == 1)
        {
            cell.dayLabel.text  = "Monday"
        }
        else if(days.objectAtIndex(indexPath.row) as! Int == 2)
        {
            cell.dayLabel.text  = "Tuesday"
        }
        else if(days.objectAtIndex(indexPath.row) as! Int == 3)
        {
            cell.dayLabel.text  = "Wednesday"
        }
        else if(days.objectAtIndex(indexPath.row) as! Int == 4)
        {
            cell.dayLabel.text  = "Thursday"
        }
        else if(days.objectAtIndex(indexPath.row) as! Int == 5)
        {
            cell.dayLabel.text  = "Friday"
        }
        else if(days.objectAtIndex(indexPath.row) as! Int == 6)
        {
            cell.dayLabel.text  = "Saturday"
        }
        else if(days.objectAtIndex(indexPath.row) as! Int == 7)
        {
            cell.dayLabel.text  = "Sunday"
        }
        cell.img.frame = CGRectMake(cell.img.frame.origin.x, cell.img.frame.origin.y, self.view.frame.size.width - 20, cell.img.frame.size.height)
        cell.view.frame = CGRectMake(cell.view.frame.origin.x, cell.view.frame.origin.y, self.view.frame.size.width - 20 , cell.view.frame.size.height)
        cell.sideButton.frame = CGRectMake(self.view.frame.size.width - 70, cell.sideButton.frame.origin.y, cell.sideButton.frame.size.width , cell.sideButton.frame.size.height)
        cell.swi1.frame = CGRectMake(self.view.frame.size.width - 80, cell.swi1.frame.origin.y, cell.swi1.frame.size.width , cell.swi1.frame.size.height)
         cell.swi2.frame = CGRectMake(self.view.frame.size.width - 80, cell.swi2.frame.origin.y, cell.swi2.frame.size.width , cell.swi2.frame.size.height)
         cell.swi3.frame = CGRectMake(self.view.frame.size.width - 80, cell.swi3.frame.origin.y, cell.swi3.frame.size.width , cell.swi3.frame.size.height)
         cell.swi4.frame = CGRectMake(self.view.frame.size.width - 80, cell.swi4.frame.origin.y, cell.swi4.frame.size.width , cell.swi4.frame.size.height)
         cell.swi5.frame = CGRectMake(self.view.frame.size.width - 80, cell.swi5.frame.origin.y, cell.swi5.frame.size.width , cell.swi5.frame.size.height)
         cell.swi6.frame = CGRectMake(self.view.frame.size.width - 80, cell.swi6.frame.origin.y, cell.swi6.frame.size.width , cell.swi6.frame.size.height)
        cell.img2.frame = CGRectMake(cell.img2.frame.origin.x, cell.img2.frame.origin.y, self.view.frame.size.width - 20, cell.img2.frame.size.height)
        cell.sideButton.addTarget(self, action: Selector("selection:"), forControlEvents: UIControlEvents.TouchUpInside)
        cell.sideButton.tag = indexPath.row
        if(selected == indexPath.row)
        {
            cell.sideButton.setImage(UIImage(named: "green-arrow.png"), forState: UIControlState.Normal)
            cell.view.alpha = 1
            
            if(indexPath.row == 0)
            {
                    if(day1[0] == 0)
                    {
                      cell.swi1.setOn(false, animated: true)
                    }
                    else
                    {
                      cell.swi1.setOn(true, animated: true)
                    }
                    if(day1[1] == 0)
                    {
                        cell.swi2.setOn(false, animated: true)
                    }
                    else
                    {
                        cell.swi2.setOn(true, animated: true)
                    }
                    if(day1[2] == 0)
                    {
                        cell.swi3.setOn(false, animated: true)
                    }
                    else
                    {
                        cell.swi3.setOn(true, animated: true)
                    }
                    if(day1[3] == 0)
                    {
                        cell.swi4.setOn(false, animated: true)
                    }
                    else
                    {
                        cell.swi4.setOn(true, animated: true)
                    }
                    if(day1[4] == 0)
                    {
                        cell.swi5.setOn(false, animated: true)
                    }
                    else
                    {
                        cell.swi5.setOn(true, animated: true)
                    }
                    if(day1[5] == 0)
                    {
                        cell.swi6.setOn(false, animated: true)
                    }
                    else
                    {
                        cell.swi6.setOn(true, animated: true)
                    }
    
            }
            if(indexPath.row == 1)
            {
                if(day2[0] == 0)
                {
                    cell.swi1.setOn(false, animated: true)
                }
                else
                {
                    cell.swi1.setOn(true, animated: true)
                }
                if(day2[1] == 0)
                {
                    cell.swi2.setOn(false, animated: true)
                }
                else
                {
                    cell.swi2.setOn(true, animated: true)
                }
                if(day2[2] == 0)
                {
                    cell.swi3.setOn(false, animated: true)
                }
                else
                {
                    cell.swi3.setOn(true, animated: true)
                }
                if(day2[3] == 0)
                {
                    cell.swi4.setOn(false, animated: true)
                }
                else
                {
                    cell.swi4.setOn(true, animated: true)
                }
                if(day2[4] == 0)
                {
                    cell.swi5.setOn(false, animated: true)
                }
                else
                {
                    cell.swi5.setOn(true, animated: true)
                }
                if(day2[5] == 0)
                {
                    cell.swi6.setOn(false, animated: true)
                }
                else
                {
                    cell.swi6.setOn(true, animated: true)
                }
                
            }
            if(indexPath.row == 2)
            {
                if(day3[0] == 0)
                {
                    cell.swi1.setOn(false, animated: true)
                }
                else
                {
                    cell.swi1.setOn(true, animated: true)
                }
                if(day3[1] == 0)
                {
                    cell.swi2.setOn(false, animated: true)
                }
                else
                {
                    cell.swi2.setOn(true, animated: true)
                }
                if(day3[2] == 0)
                {
                    cell.swi3.setOn(false, animated: true)
                }
                else
                {
                    cell.swi3.setOn(true, animated: true)
                }
                if(day3[3] == 0)
                {
                    cell.swi4.setOn(false, animated: true)
                }
                else
                {
                    cell.swi4.setOn(true, animated: true)
                }
                if(day3[4] == 0)
                {
                    cell.swi5.setOn(false, animated: true)
                }
                else
                {
                    cell.swi5.setOn(true, animated: true)
                }
                if(day3[5] == 0)
                {
                    cell.swi6.setOn(false, animated: true)
                }
                else
                {
                    cell.swi6.setOn(true, animated: true)
                }
                
            }
            if(indexPath.row == 3)
            {
                if(day4[0] == 0)
                {
                    cell.swi1.setOn(false, animated: true)
                }
                else
                {
                    cell.swi1.setOn(true, animated: true)
                }
                if(day4[1] == 0)
                {
                    cell.swi2.setOn(false, animated: true)
                }
                else
                {
                    cell.swi2.setOn(true, animated: true)
                }
                if(day4[2] == 0)
                {
                    cell.swi3.setOn(false, animated: true)
                }
                else
                {
                    cell.swi3.setOn(true, animated: true)
                }
                if(day4[3] == 0)
                {
                    cell.swi4.setOn(false, animated: true)
                }
                else
                {
                    cell.swi4.setOn(true, animated: true)
                }
                if(day4[4] == 0)
                {
                    cell.swi5.setOn(false, animated: true)
                }
                else
                {
                    cell.swi5.setOn(true, animated: true)
                }
                if(day4[5] == 0)
                {
                    cell.swi6.setOn(false, animated: true)
                }
                else
                {
                    cell.swi6.setOn(true, animated: true)
                }
            }
            if(indexPath.row == 4)
            {
                if(day5[0] == 0)
                {
                    cell.swi1.setOn(false, animated: true)
                }
                else
                {
                    cell.swi1.setOn(true, animated: true)
                }
                if(day5[1] == 0)
                {
                    cell.swi2.setOn(false, animated: true)
                }
                else
                {
                    cell.swi2.setOn(true, animated: true)
                }
                if(day5[2] == 0)
                {
                    cell.swi3.setOn(false, animated: true)
                }
                else
                {
                    cell.swi3.setOn(true, animated: true)
                }
                if(day5[3] == 0)
                {
                    cell.swi4.setOn(false, animated: true)
                }
                else
                {
                    cell.swi4.setOn(true, animated: true)
                }
                if(day5[4] == 0)
                {
                    cell.swi5.setOn(false, animated: true)
                }
                else
                {
                    cell.swi5.setOn(true, animated: true)
                }
                if(day5[5] == 0)
                {
                    cell.swi6.setOn(false, animated: true)
                }
                else
                {
                    cell.swi6.setOn(true, animated: true)
                }
            }
            if(indexPath.row == 5)
            {
                if(day6[0] == 0)
                {
                    cell.swi1.setOn(false, animated: true)
                }
                else
                {
                    cell.swi1.setOn(true, animated: true)
                }
                if(day6[1] == 0)
                {
                    cell.swi2.setOn(false, animated: true)
                }
                else
                {
                    cell.swi2.setOn(true, animated: true)
                }
                if(day6[2] == 0)
                {
                    cell.swi3.setOn(false, animated: true)
                }
                else
                {
                    cell.swi3.setOn(true, animated: true)
                }
                if(day6[3] == 0)
                {
                    cell.swi4.setOn(false, animated: true)
                }
                else
                {
                    cell.swi4.setOn(true, animated: true)
                }
                if(day6[4] == 0)
                {
                    cell.swi5.setOn(false, animated: true)
                }
                else
                {
                    cell.swi5.setOn(true, animated: true)
                }
                if(day6[5] == 0)
                {
                    cell.swi6.setOn(false, animated: true)
                }
                else
                {
                    cell.swi6.setOn(true, animated: true)
                }
            }
            if(indexPath.row == 6)
            {
                if(day7[0] == 0)
                {
                    cell.swi1.setOn(false, animated: true)
                }
                else
                {
                    cell.swi1.setOn(true, animated: true)
                }
                if(day7[1] == 0)
                {
                    cell.swi2.setOn(false, animated: true)
                }
                else
                {
                    cell.swi2.setOn(true, animated: true)
                }
                if(day7[2] == 0)
                {
                    cell.swi3.setOn(false, animated: true)
                }
                else
                {
                    cell.swi3.setOn(true, animated: true)
                }
                if(day7[3] == 0)
                {
                    cell.swi4.setOn(false, animated: true)
                }
                else
                {
                    cell.swi4.setOn(true, animated: true)
                }
                if(day7[4] == 0)
                {
                    cell.swi5.setOn(false, animated: true)
                }
                else
                {
                    cell.swi5.setOn(true, animated: true)
                }
                if(day7[5] == 0)
                {
                    cell.swi6.setOn(false, animated: true)
                }
                else
                {
                    cell.swi6.setOn(true, animated: true)
                }
            }

            cell.swi1.addTarget(self, action: Selector("switchAction:"), forControlEvents: UIControlEvents.ValueChanged)
            cell.swi1.tag = "\(indexPath.row+1)1".toInt()!
            cell.swi2.addTarget(self, action: Selector("switchAction:"), forControlEvents: UIControlEvents.ValueChanged)
            cell.swi2.tag = "\(indexPath.row+1)2".toInt()!
            cell.swi3.addTarget(self, action: Selector("switchAction:"), forControlEvents: UIControlEvents.ValueChanged)
            cell.swi3.tag = "\(indexPath.row+1)3".toInt()!
            cell.swi4.addTarget(self, action: Selector("switchAction:"), forControlEvents: UIControlEvents.ValueChanged)
            cell.swi4.tag = "\(indexPath.row+1)4".toInt()!
            cell.swi5.addTarget(self, action: Selector("switchAction:"), forControlEvents: UIControlEvents.ValueChanged)
            cell.swi5.tag = "\(indexPath.row+1)5".toInt()!
            cell.swi6.addTarget(self, action: Selector("switchAction:"), forControlEvents: UIControlEvents.ValueChanged)
            cell.swi6.tag = "\(indexPath.row+1)6".toInt()!
        }
        else
        {
            cell.sideButton.setImage(UIImage(named: "arrow-gray.png"), forState: UIControlState.Normal)
            cell.view.alpha = 0
        }
        return cell
        
    }
    
    func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
        if(selected == indexPath.row)
        {
        return 290
        }
        else
        {
        return 55
        }
    }
    
    func selection(sender:UIButton)
    {
       self.selected = sender.tag
       self.tableView.reloadData()
    }

    func switchAction(sender:UISwitch)
    {
        var data:String = String(sender.tag)
        var first:String = String(data[advance(data.startIndex, 0)])
        var Second:String = String(data[advance(data.startIndex, 1)])
//        println(first)
//        println(Second)
        var State:String!
        if(sender.on)
        {
            State = "on"
        }
        else
        {
            State = "off"
        }
        
        if(first.toInt() == 1)
        {
            if(Second.toInt() == 1)
            {
                if(State == "on")
                {
                    day1[0] = 1
                }
                else
                {
                    day1[0] = 0
                }
            }
            if(Second.toInt() == 2)
            {
                if(State == "on")
                {
                    day1[1] = 1
                }
                else
                {
                    day1[1] = 0
                }
            }
            if(Second.toInt() == 3)
            {
                if(State == "on")
                {
                    day1[2] = 1
                }
                else
                {
                    day1[2] = 0
                }
            }
            if(Second.toInt() == 4)
            {
                if(State == "on")
                {
                    day1[3] = 1
                }
                else
                {
                    day1[3] = 0
                }
            }
            if(Second.toInt() == 5)
            {
                if(State == "on")
                {
                    day1[4] = 1
                }
                else
                {
                    day1[4] = 0
                }
            }
            if(Second.toInt() == 6)
            {
                if(State == "on")
                {
                    day1[5] = 1
                }
                else
                {
                    day1[5] = 0
                }
            }
        }
        if(first.toInt() == 2)
        {
            if(Second.toInt() == 1)
            {
                if(State == "on")
                {
                    day2[0] = 1
                }
                else
                {
                    day2[0] = 0
                }
            }
            if(Second.toInt() == 2)
            {
                if(State == "on")
                {
                    day2[1] = 1
                }
                else
                {
                    day2[1] = 0
                }
            }
            if(Second.toInt() == 3)
            {
                if(State == "on")
                {
                    day2[2] = 1
                }
                else
                {
                    day2[2] = 0
                }
            }
            if(Second.toInt() == 4)
            {
                if(State == "on")
                {
                    day2[3] = 1
                }
                else
                {
                    day2[3] = 0
                }
            }
            if(Second.toInt() == 5)
            {
                if(State == "on")
                {
                    day2[4] = 1
                }
                else
                {
                    day2[4] = 0
                }
            }
            if(Second.toInt() == 6)
            {
                if(State == "on")
                {
                    day2[5] = 1
                }
                else
                {
                    day2[5] = 0
                }
            }
        }
        if(first.toInt() == 3)
        {
            if(Second.toInt() == 1)
            {
                if(State == "on")
                {
                    day3[0] = 1
                }
                else
                {
                    day3[0] = 0
                }
            }
            if(Second.toInt() == 2)
            {
                if(State == "on")
                {
                    day3[1] = 1
                }
                else
                {
                    day3[1] = 0
                }
            }
            if(Second.toInt() == 3)
            {
                if(State == "on")
                {
                    day3[2] = 1
                }
                else
                {
                    day3[2] = 0
                }
            }
            if(Second.toInt() == 4)
            {
                if(State == "on")
                {
                    day3[3] = 1
                }
                else
                {
                    day3[3] = 0
                }
            }
            if(Second.toInt() == 5)
            {
                if(State == "on")
                {
                    day3[4] = 1
                }
                else
                {
                    day3[4] = 0
                }
            }
            if(Second.toInt() == 6)
            {
                if(State == "on")
                {
                    day3[5] = 1
                }
                else
                {
                    day3[5] = 0
                }
            }
        }
        if(first.toInt() == 4)
        {
            if(Second.toInt() == 1)
            {
                if(State == "on")
                {
                    day4[0] = 1
                }
                else
                {
                    day4[0] = 0
                }
            }
            if(Second.toInt() == 2)
            {
                if(State == "on")
                {
                    day4[1] = 1
                }
                else
                {
                    day4[1] = 0
                }
            }
            if(Second.toInt() == 3)
            {
                if(State == "on")
                {
                    day4[2] = 1
                }
                else
                {
                    day4[2] = 0
                }
            }
            if(Second.toInt() == 4)
            {
                if(State == "on")
                {
                    day4[3] = 1
                }
                else
                {
                    day4[3] = 0
                }
            }
            if(Second.toInt() == 5)
            {
                if(State == "on")
                {
                    day4[4] = 1
                }
                else
                {
                    day4[4] = 0
                }
            }
            if(Second.toInt() == 6)
            {
                if(State == "on")
                {
                    day4[5] = 1
                }
                else
                {
                    day4[5] = 0
                }
            }
        }
        if(first.toInt() == 5)
        {
            if(Second.toInt() == 1)
            {
                if(State == "on")
                {
                    day5[0] = 1
                }
                else
                {
                    day5[0] = 0
                }
            }
            if(Second.toInt() == 2)
            {
                if(State == "on")
                {
                    day5[1] = 1
                }
                else
                {
                    day5[1] = 0
                }
            }
            if(Second.toInt() == 3)
            {
                if(State == "on")
                {
                    day5[2] = 1
                }
                else
                {
                    day5[2] = 0
                }
            }
            if(Second.toInt() == 4)
            {
                if(State == "on")
                {
                    day5[3] = 1
                }
                else
                {
                    day5[3] = 0
                }
            }
            if(Second.toInt() == 5)
            {
                if(State == "on")
                {
                    day5[4] = 1
                }
                else
                {
                    day5[4] = 0
                }
            }
            if(Second.toInt() == 6)
            {
                if(State == "on")
                {
                    day5[5] = 1
                }
                else
                {
                    day5[5] = 0
                }
            }
        }
        if(first.toInt() == 6)
        {
            if(Second.toInt() == 1)
            {
                if(State == "on")
                {
                    day6[0] = 1
                }
                else
                {
                    day6[0] = 0
                }
            }
            if(Second.toInt() == 2)
            {
                if(State == "on")
                {
                    day6[1] = 1
                }
                else
                {
                    day6[1] = 0
                }
            }
            if(Second.toInt() == 3)
            {
                if(State == "on")
                {
                    day6[2] = 1
                }
                else
                {
                    day6[2] = 0
                }
            }
            if(Second.toInt() == 4)
            {
                if(State == "on")
                {
                    day6[3] = 1
                }
                else
                {
                    day6[3] = 0
                }
            }
            if(Second.toInt() == 5)
            {
                if(State == "on")
                {
                    day6[4] = 1
                }
                else
                {
                    day6[4] = 0
                }
            }
            if(Second.toInt() == 6)
            {
                if(State == "on")
                {
                    day6[5] = 1
                }
                else
                {
                    day6[5] = 0
                }
            }
        }
        if(first.toInt() == 7)
        {
            if(Second.toInt() == 1)
            {
                if(State == "on")
                {
                    day7[0] = 1
                }
                else
                {
                    day7[0] = 0
                }
            }
            if(Second.toInt() == 2)
            {
                if(State == "on")
                {
                    day7[1] = 1
                }
                else
                {
                    day7[1] = 0
                }
            }
            if(Second.toInt() == 3)
            {
                if(State == "on")
                {
                    day7[2] = 1
                }
                else
                {
                    day7[2] = 0
                }
            }
            if(Second.toInt() == 4)
            {
                if(State == "on")
                {
                    day7[3] = 1
                }
                else
                {
                    day7[3] = 0
                }
            }
            if(Second.toInt() == 5)
            {
                if(State == "on")
                {
                    day7[4] = 1
                }
                else
                {
                    day7[4] = 0
                }
            }
            if(Second.toInt() == 6)
            {
                if(State == "on")
                {
                    day7[5] = 1
                }
                else
                {
                    day7[5] = 0
                }
            }
        }
//        println(day1)
//        println(day2)
//        println(day3)
//        println(day4)
//        println(day5)
//        println(day6)
//        println(day7)
    }
    
    
    @IBAction func PreviousButton(sender: AnyObject)
    {
        self.navigationController?.popViewControllerAnimated(true)
    }
    
    @IBAction func Next(sender: AnyObject)
    {
        var screen:porterRegistrationStep5 = self.storyboard?.instantiateViewControllerWithIdentifier("porterRegister5") as! porterRegistrationStep5
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
