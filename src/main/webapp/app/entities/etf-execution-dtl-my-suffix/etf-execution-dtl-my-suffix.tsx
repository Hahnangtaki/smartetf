import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etf-execution-dtl-my-suffix.reducer';
import { IEtfExecutionDtlMySuffix } from 'app/shared/model/etf-execution-dtl-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfExecutionDtlMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EtfExecutionDtlMySuffix extends React.Component<IEtfExecutionDtlMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { etfExecutionDtlList, match } = this.props;
    return (
      <div>
        <h2 id="etf-execution-dtl-my-suffix-heading">
          Etf Execution Dtls
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Etf Execution Dtl
          </Link>
        </h2>
        <div className="table-responsive">
          {etfExecutionDtlList && etfExecutionDtlList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Basket Order Id</th>
                  <th>Transaction Id</th>
                  <th>Transaction Type</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {etfExecutionDtlList.map((etfExecutionDtl, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${etfExecutionDtl.id}`} color="link" size="sm">
                        {etfExecutionDtl.id}
                      </Button>
                    </td>
                    <td>{etfExecutionDtl.basketOrderId}</td>
                    <td>{etfExecutionDtl.transactionId}</td>
                    <td>{etfExecutionDtl.transactionType}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${etfExecutionDtl.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfExecutionDtl.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfExecutionDtl.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Etf Execution Dtls found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ etfExecutionDtl }: IRootState) => ({
  etfExecutionDtlList: etfExecutionDtl.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfExecutionDtlMySuffix);
