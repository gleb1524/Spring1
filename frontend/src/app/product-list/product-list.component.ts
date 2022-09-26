import {Component, OnInit} from '@angular/core';
import {Product} from "../model/product";
import {ProductServiceComponent} from "../product-service/product-service.component";
import {Router} from "@angular/router";


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductServiceComponent,
              private location: Router) {
  }


  ngOnInit(): void {
    this.productService.findAll()
      .subscribe(response => {
        this.products = response.content;
      }, error => {
        console.log(error);
      })
  }

  public delete(id: number | null) {
    if (id != null) {
      this.productService.delete(id)
        .subscribe(res => {
            console.log(res);
          },
          error => {
            console.log(error);
          })
    }
  }

}
