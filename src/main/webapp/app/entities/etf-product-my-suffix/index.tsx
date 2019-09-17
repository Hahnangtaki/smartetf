import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtfProductMySuffix from './etf-product-my-suffix';
import EtfProductMySuffixDetail from './etf-product-my-suffix-detail';
import EtfProductMySuffixUpdate from './etf-product-my-suffix-update';
import EtfProductMySuffixDeleteDialog from './etf-product-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtfProductMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtfProductMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtfProductMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtfProductMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EtfProductMySuffixDeleteDialog} />
  </>
);

export default Routes;
