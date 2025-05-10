import { ProductCategory } from "./product-category";

export class Product {
    constructor(
        public id: number,
        public name: string,
        public sku: string,
        public description: string,
        public price: number,
        public imageUrl: string,
        public isActive: boolean,
        public unitsInStock: number,
        public createdAt: Date,
        public updatedAt: Date,
        public category:ProductCategory
    ){}
}
