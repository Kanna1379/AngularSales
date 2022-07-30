import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SalesPerson } from '../sales-person';

@Component({
  selector: 'app-sales-person-list',
  templateUrl: './sales-person-list.component.html',
  styleUrls: ['./sales-person-list.component.css']
})
export class SalesPersonListComponent implements OnInit {
  baseLineSal:number=30000;
  constructor() { }
  
  salesPersonList :SalesPerson[]=[
    new SalesPerson("Geethika","Kanna","geethika.kanna@gmail.com",45000,5000),
    new SalesPerson("Arun","Kumar","arun.kumar@gmail.com",35000,2000),
    new SalesPerson("Vijay","Yadav","vijay.yadav@gmail.com",25000,1000),
    new SalesPerson("Surya","Singh","surya.singh@gmail.com",50000,3000),
    new SalesPerson("Sureka","Kona","sureka.kona@gmail.com",40000,6000)

  ]

  ngOnInit(): void {
  }

  onSubmit(form:NgForm){
  this.salesPersonList.push(form.value);
  }

}
