import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtfProductDealerMySuffix from './etf-product-dealer-my-suffix';
import EtfProductDealerMySuffixDetail from './etf-product-dealer-my-suffix-detail';
import EtfProductDealerMySuffixUpdate from './etf-product-dealer-my-suffix-update';
import EtfProductDealerMySuffixDeleteDialog from './etf-product-dealer-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtfProductDealerMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtfProductDealerMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtfProductDealerMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtfProductDealerMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EtfProductDealerMySuffixDeleteDialog} />
  </>
);

export default Routes;
