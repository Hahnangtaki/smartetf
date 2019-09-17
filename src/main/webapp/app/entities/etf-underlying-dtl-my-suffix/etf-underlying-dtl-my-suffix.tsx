import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etf-underlying-dtl-my-suffix.reducer';
import { IEtfUnderlyingDtlMySuffix } from 'app/shared/model/etf-underlying-dtl-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfUnderlyingDtlMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EtfUnderlyingDtlMySuffix extends React.Component<IEtfUnderlyingDtlMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { etfUnderlyingDtlList, match } = this.props;
    return (
      <div>
        <h2 id="etf-underlying-dtl-my-suffix-heading">
          Etf Underlying Dtls
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Etf Underlying Dtl
          </Link>
        </h2>
        <div className="table-responsive">
          {etfUnderlyingDtlList && etfUnderlyingDtlList.length > 0 ? (
            <Table responsive aria-describedby="etf-underlying-dtl-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Stock Code</th>
                  <th>Weight</th>
                  <th>Etf Underlying</th>
                  <th>Etf Underlying</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {etfUnderlyingDtlList.map((etfUnderlyingDtl, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${etfUnderlyingDtl.id}`} color="link" size="sm">
                        {etfUnderlyingDtl.id}
                      </Button>
                    </td>
                    <td>{etfUnderlyingDtl.stockCode}</td>
                    <td>{etfUnderlyingDtl.weight}</td>
                    <td>
                      {etfUnderlyingDtl.etfUnderlyingId ? (
                        <Link to={`etf-underlying-my-suffix/${etfUnderlyingDtl.etfUnderlyingId}`}>{etfUnderlyingDtl.etfUnderlyingId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {etfUnderlyingDtl.etfUnderlyingId ? (
                        <Link to={`etf-underlying-my-suffix/${etfUnderlyingDtl.etfUnderlyingId}`}>{etfUnderlyingDtl.etfUnderlyingId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${etfUnderlyingDtl.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfUnderlyingDtl.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfUnderlyingDtl.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Etf Underlying Dtls found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ etfUnderlyingDtl }: IRootState) => ({
  etfUnderlyingDtlList: etfUnderlyingDtl.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfUnderlyingDtlMySuffix);
