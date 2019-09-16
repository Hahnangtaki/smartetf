import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtfUnderlyingMySuffix from './etf-underlying-my-suffix';
import EtfUnderlyingMySuffixDetail from './etf-underlying-my-suffix-detail';
import EtfUnderlyingMySuffixUpdate from './etf-underlying-my-suffix-update';
import EtfUnderlyingMySuffixDeleteDialog from './etf-underlying-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtfUnderlyingMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtfUnderlyingMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtfUnderlyingMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtfUnderlyingMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EtfUnderlyingMySuffixDeleteDialog} />
  </>
);

export default Routes;
