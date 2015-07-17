//
//  LoginInOrRegister.swift
//  porter
//
//  Created by Manohar Kola on 06/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class LoginInOrRegister: UIViewController,PagedFlowViewDataSource,PagedFlowViewDelegate{

    
    @IBOutlet var SwipeView: PagedFlowView!
    @IBOutlet var pageController: UIPageControl!

    
    @IBOutlet var SwipeleftView: UIView!
    @IBOutlet var appearView: UIView!
    
    
    
    var ud = NSUserDefaults.standardUserDefaults()
    
    override func viewDidLoad() {
        super.viewDidLoad()

       
        SwipeView.delegate = self
        SwipeView.dataSource = self
        SwipeView.pageControl = pageController

        
        var swipeRecognizer:UISwipeGestureRecognizer = UISwipeGestureRecognizer(target: self, action: Selector("swipeLeft"))
        swipeRecognizer.direction = UISwipeGestureRecognizerDirection.Left
        self.SwipeleftView.addGestureRecognizer(swipeRecognizer)
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //Swipe Recognizer
    
    func swipeLeft()
    {
        var destination: CGRect = SwipeleftView.frame
        if (destination.origin.x >= 0)
        {
            destination.origin.x = -500
        }
        UIView.animateWithDuration(0.5, animations: {
            self.SwipeleftView.frame = destination
            self.appearView.frame.origin.x = 0
            }, completion: { (finished: Bool) in
                self.view.userInteractionEnabled = true
        })
    }
    
    
    //PageFlow Delegate methods
    
    func sizeForPageInFlowView(flowView: PagedFlowView!) -> CGSize {

       return CGSize(width: SwipeView.frame.size.width, height: SwipeView.frame.size.height)
    }

    func flowView(flowView: PagedFlowView!, didScrollToPageAtIndex index: Int) {
        
        
    }
    func numberOfPagesInFlowView(flowView: PagedFlowView!) -> Int {
        
        return 4
    }
    
    func flowView(flowView: PagedFlowView!, cellForPageAtIndex index: Int) -> UIView! {
        
        var imageView: UIImageView! = flowView.dequeueReusableCell() as? UIImageView
        
        if !(imageView != nil) {
            imageView = UIImageView()
            imageView.layer.cornerRadius = 1.0
            imageView.layer.masksToBounds = true
        }
        if(index == 0)
        {
            imageView.image = UIImage(named: "tutorial-1.png")
        }
        else if(index == 1)
        {
            imageView.image = UIImage(named: "tutorial_2.png")
        }
        else if(index == 2)
        {
            imageView.image = UIImage(named: "tutorial-3.png")
        }
        else
        {
            imageView.image = UIImage(named: "tutorial-4.png")
        }
        imageView.contentMode = UIViewContentMode.Center
        return imageView
    }
    
    
    //Button Actions
    
    @IBAction func signIn(sender: AnyObject)
    {
        var screen3:loginScreen = self.storyboard?.instantiateViewControllerWithIdentifier("screen3") as! loginScreen
        self.navigationController?.pushViewController(screen3, animated: true)
    }
    
    @IBAction func register(sender: AnyObject)
    {
        if(ud.valueForKey("type") as? String == "user")
        {
        var screen3:UserRegistrationStep1 = self.storyboard?.instantiateViewControllerWithIdentifier("userRegister1") as! UserRegistrationStep1
        self.navigationController?.pushViewController(screen3, animated: true)
        }
        else
        {
        var screen3:porterRegistrationStep1 = self.storyboard?.instantiateViewControllerWithIdentifier("porterRegister1") as! porterRegistrationStep1
        self.navigationController?.pushViewController(screen3, animated: true)
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
