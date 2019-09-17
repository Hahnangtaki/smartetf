import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etf-underlying-dtl-my-suffix.reducer';
import { IEtfUnderlyingDtlMySuffix } from 'app/shared/model/etf-underlying-dtl-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfUnderlyingDtlMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EtfUnderlyingDtlMySuffixDetail extends React.Component<IEtfUnderlyingDtlMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { etfUnderlyingDtlEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EtfUnderlyingDtl [<b>{etfUnderlyingDtlEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="stockCode">Stock Code</span>
            </dt>
            <dd>{etfUnderlyingDtlEntity.stockCode}</dd>
            <dt>
              <span id="weight">Weight</span>
            </dt>
            <dd>{etfUnderlyingDtlEntity.weight}</dd>
            <dt>Etf Underlying</dt>
            <dd>{etfUnderlyingDtlEntity.etfUnderlyingId ? etfUnderlyingDtlEntity.etfUnderlyingId : ''}</dd>
            <dt>Etf Underlying</dt>
            <dd>{etfUnderlyingDtlEntity.etfUnderlyingId ? etfUnderlyingDtlEntity.etfUnderlyingId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/etf-underlying-dtl-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/etf-underlying-dtl-my-suffix/${etfUnderlyingDtlEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ etfUnderlyingDtl }: IRootState) => ({
  etfUnderlyingDtlEntity: etfUnderlyingDtl.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfUnderlyingDtlMySuffixDetail);
