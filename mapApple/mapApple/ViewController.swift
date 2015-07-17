//
//  ViewController.swift
//  mapApple
//
//  Created by Manohar Kola on 18/04/15.
//  Copyright (c) 2015 relish. All rights reserved.
//

import UIKit
import MapKit
class ViewController: UIViewController,MKMapViewDelegate {
@IBOutlet var mapView: MKMapView!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let location = CLLocationCoordinate2D(latitude:16.355615 , longitude: 82.1684613)
        let annotation = MKPointAnnotation()
        annotation.coordinate = location
        annotation.title =   "Title"
        mapView.addAnnotation(annotation)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func mapView(mapView: MKMapView!, viewForAnnotation annotation: MKAnnotation!) -> MKAnnotationView! {
        
        
        if (annotation is MKUserLocation)
        {
            return nil
        }
        var anView = mapView.dequeueReusableAnnotationViewWithIdentifier("sample")
        if anView == nil {
            anView = MKAnnotationView(annotation: annotation, reuseIdentifier: "sample")
           anView.image = UIImage(named: "")
        }
        return anView
        
        
    }

}

