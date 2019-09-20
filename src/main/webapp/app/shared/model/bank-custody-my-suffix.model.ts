import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';

export interface IBankCustodyMySuffix {
  id?: number;
  custodyCode?: string;
  custodiName?: string;
  etfProducts?: IEtfProductMySuffix[];
}

export const defaultValue: Readonly<IBankCustodyMySuffix> = {};
