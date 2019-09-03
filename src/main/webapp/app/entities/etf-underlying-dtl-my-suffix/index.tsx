import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EtfUnderlyingDtlMySuffix from './etf-underlying-dtl-my-suffix';
import EtfUnderlyingDtlMySuffixDetail from './etf-underlying-dtl-my-suffix-detail';
import EtfUnderlyingDtlMySuffixUpdate from './etf-underlying-dtl-my-suffix-update';
import EtfUnderlyingDtlMySuffixDeleteDialog from './etf-underlying-dtl-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EtfUnderlyingDtlMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EtfUnderlyingDtlMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EtfUnderlyingDtlMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={EtfUnderlyingDtlMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EtfUnderlyingDtlMySuffixDeleteDialog} />
  </>
);

export default Routes;
