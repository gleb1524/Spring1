import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {ProductListComponent} from "./product-list/product-list.component";
import {ProductFormComponent} from "./product-form/product-form.component";
import {ProductServiceComponent} from "./product-service/product-service.component";

const routes: Routes = [
  {path: "", pathMatch:"full", redirectTo:"product"},
  {path: "product", component: ProductListComponent},
  {path: "product/:id", component: ProductFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
