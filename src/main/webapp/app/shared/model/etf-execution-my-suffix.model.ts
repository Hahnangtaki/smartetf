export interface IEtfExecutionMySuffix {
  id?: number;
  basketOrderId?: number;
  stockCode?: string;
  qty?: number;
  qtyOdd?: number;
  price?: number;
  amount?: number;
}

export const defaultValue: Readonly<IEtfExecutionMySuffix> = {};
