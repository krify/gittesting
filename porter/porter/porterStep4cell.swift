//
//  porterStep4cell.swift
//  porter
//
//  Created by Manohar Kola on 15/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class porterStep4cell: UITableViewCell {

    @IBOutlet var dayLabel: UILabel!
    @IBOutlet var sideButton: UIButton!
    
    @IBOutlet var swi1: UISwitch!
    @IBOutlet var swi2: UISwitch!
    @IBOutlet var swi3: UISwitch!
    @IBOutlet var swi4: UISwitch!
    @IBOutlet var swi5: UISwitch!
    @IBOutlet var swi6: UISwitch!
    @IBOutlet var view: UIView!
    
    @IBOutlet var img: UIImageView!
    @IBOutlet var img2: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
