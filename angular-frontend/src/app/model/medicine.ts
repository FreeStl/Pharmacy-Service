import {MedClass} from './model-enums/med-class';
import {MedType} from './model-enums/med-type';

export class Medicine {
  createdAt: Date;
  updatedAt: Date;
  id: number;
  medType: MedType;
  medClass: MedClass;
  name: string;
  amout: number;
  price: number;
  soldCount: number;
}
