import { Component, OnInit } from '@angular/core';
import {ProductServiceComponent} from "../product-service/product-service.component";
import {Product} from "../model/product";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  product = new Product(null, "", 0);

  constructor(private productService: ProductServiceComponent,
              private  route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      this.productService.findById(param['id'])
        .subscribe(res => {
          this.product = res;
        }, error => {
          console.log(error);
        })
    })
  }

  save(){
    this.productService.save(this.product)
      .subscribe(res => {
        console.log(res);
        this.router.navigateByUrl('/product');
      }, error => {
        console.log(error);
      })
  }



}
